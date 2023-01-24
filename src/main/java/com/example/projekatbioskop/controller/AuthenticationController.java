package com.example.projekatbioskop.controller;

import com.example.projekatbioskop.jwt.JwtRequest;
import com.example.projekatbioskop.jwt.JwtResponse;
import com.example.projekatbioskop.jwt.JwtTokenUtil;
import com.example.projekatbioskop.model.Film;
import com.example.projekatbioskop.security.CustomUserDetails;
import com.example.projekatbioskop.service.UserDetailServiceImpl;
import com.example.projekatbioskop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller

public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailServiceImpl userDetailsService;

    @GetMapping(value = "/")
    public String auth(@AuthenticationPrincipal CustomUserDetails loggedUser, Model theModel){
        theModel.addAttribute("username","");
        theModel.addAttribute("password","");
        return "authenticationForm";
    }

      @PostMapping(value = "/authform")
   public String createAuthenticationToken( @ModelAttribute("username") String username, @ModelAttribute("password") String password,
                                           final HttpServletResponse responseEntity) throws Exception {
          JwtResponse response=new JwtResponse();
          String username1=username;
          String password1=password;
       Authentication auth= authenticate(username1, password1);
          System.out.println(auth.getPrincipal());
       final UserDetails userDetails = userDetailsService.loadUserByUsername(username1);

       final String token = jwtTokenUtil.generateToken(userDetails);
          response.setJwtToken(token);

          storeTokenInCookie(token,responseEntity);
          return "login";
   }

    private void storeTokenInCookie(final String token, final HttpServletResponse response) {
        final Cookie accessToken = new Cookie("access_token", token);
        accessToken.setHttpOnly(true);
        accessToken.setSecure(false);
        accessToken.setPath("/");
        accessToken.setMaxAge(Integer.MAX_VALUE*1000);
     //   System.out.println("dosli smo do ovde");
    //    System.out.println(accessToken.getValue());
        final Cookie tokenType = new Cookie("token_type", "Bearer");
        tokenType.setHttpOnly(true);
        tokenType.setSecure(false);
        tokenType.setPath("/");
        tokenType.setMaxAge(Integer.MAX_VALUE*1000);

        // Set Refresh Token and other required cookies.
        response.addCookie(accessToken);
        response.addCookie(tokenType);
        response.setStatus(HttpServletResponse.SC_CREATED);

    }

    private Authentication authenticate(String username, String password) throws Exception {
        try {
            Authentication auth= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return auth;
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


}
