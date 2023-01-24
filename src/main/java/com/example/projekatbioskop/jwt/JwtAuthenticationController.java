package com.example.projekatbioskop.jwt;


import com.example.projekatbioskop.model.User;

import com.example.projekatbioskop.service.UserDetailServiceImpl;
import com.example.projekatbioskop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Autowired
    private UserDetailServiceImpl userDetailsService;
    @Autowired
    private UserService userService;

    @PostMapping(value = "/auth")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest,final HttpServletResponse responseEntity) throws Exception {
JwtResponse response=new JwtResponse();

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);
        response.setJwtToken(token);
storeTokenInCookie(token,responseEntity);
       // return ResponseEntity.ok(new JwtResponse(token));
       // return ResponseEntity.ok(response);
        return ResponseEntity.ok(response);
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
    private void storeTokenInCookie(final String token, final HttpServletResponse response) {
        final Cookie accessToken = new Cookie("access_token", token.substring(7));
        accessToken.setHttpOnly(true);
        accessToken.setSecure(true);
        accessToken.setPath("/");
        accessToken.setMaxAge(Integer.MAX_VALUE*1000);

        final Cookie tokenType = new Cookie("token_type", "Bearer");
        tokenType.setHttpOnly(true);
        tokenType.setSecure(true);
        tokenType.setPath("/");
        tokenType.setMaxAge(Integer.MAX_VALUE*1000);

        // Set Refresh Token and other required cookies.
        response.addCookie(accessToken);
        response.addCookie(tokenType);
    }
    @PostMapping(value = "/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveUser(@RequestBody User user) throws Exception {
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        userService.save(user);
        return "Registred sucessfully!";
    }


}
