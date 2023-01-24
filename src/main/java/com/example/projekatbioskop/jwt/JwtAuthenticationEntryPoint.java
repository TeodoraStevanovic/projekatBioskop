package com.example.projekatbioskop.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
   // private static final long serialVersionUID = -7858869558953243875L;
@Autowired
JwtRequestFilter requestFilter;
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

     //   response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        requestFilter.setServerError(request,response);
    }
}
