package com.example.projekatbioskop.security;

import com.example.projekatbioskop.jwt.JwtTokenUtil;
import com.example.projekatbioskop.model.User;
import com.example.projekatbioskop.repository.UserRepository;
import com.example.projekatbioskop.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Set;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        for (String s: roles) {System.out.println(s); }

            if(roles.contains("ADMIN") || roles.contains("USER")){
            response.sendRedirect("/home");
        } else {
            response.sendRedirect("/home");
        }

    }
}
