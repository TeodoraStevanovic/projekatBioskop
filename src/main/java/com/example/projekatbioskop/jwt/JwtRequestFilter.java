package com.example.projekatbioskop.jwt;

import com.example.projekatbioskop.service.UserDetailServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailServiceImpl jwtUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");
       // logger.warn(requestTokenHeader);
        String username = null;
        String jwtToken = null;
        String cookieName="access_token";
        String cookieType="token_type";
       Cookie cookie=getCookie(request,cookieName);
        Cookie type=getCookie(request,cookieType);
        String cookieToken="vrednost";

        if (cookie!=null && type!=null) {
            cookieToken = type.getValue() + " " + cookie.getValue();
        }
      //  System.out.println(cookieToken);
        // JWT Token is in the form "Bearer token". Remove Bearer word and get
        // only the Token
        if ((requestTokenHeader != null && requestTokenHeader.startsWith("Bearer "))|| (cookieToken!="vrednost")) {

           if ((requestTokenHeader != null && requestTokenHeader.startsWith("Bearer "))){
               jwtToken = requestTokenHeader.substring(7);
               try {
                   username = jwtTokenUtil.getUsernameFromToken(jwtToken);

               } catch (IllegalArgumentException e) {
                   System.out.println("Unable to get JWT Token");
               } catch (ExpiredJwtException e) {
                   System.out.println("JWT Token has expired");
               }

           }else{

               // jwtToken=cookie.getValue();
               jwtToken = cookieToken.substring(7);
               try {
                   username = jwtTokenUtil.getUsernameFromToken(jwtToken);

               } catch (IllegalArgumentException e) {
                   System.out.println("Unable to get JWT Token from cookie");
               } catch (ExpiredJwtException e) {
                   System.out.println("JWT Cookie Token has expired");
               }
           }



        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }
///////////////////////////////////////////////////////////////////////////////////////////////////////
        // Once we get the token validate it.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

            // if token is valid configure Spring Security to manually set
            // authentication
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // After setting the Authentication in the context, we specify
                // that the current user is authenticated. So it passes the
                // Spring Security Configurations successfully.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
    public void setServerError(HttpServletRequest request, HttpServletResponse response/*, ErrorObject errorObject*/) throws IOException{

    HashMap<String, Object> errorAttributes=new HashMap<>();

            errorAttributes.put("timestamp", new Date().getTime());
            errorAttributes.put("path", request.getServletPath());
            errorAttributes.put("error", "Internal Server Error");
    //errorAttributes.put("status", errorObject.getStatusCode());
    // response.setStatus(errorObject.getStatusCode());
    response.setStatus(401);
     response.setHeader("Content-Type", APPLICATION_JSON_UTF8_VALUE);
    response.getWriter().write(convertObjToJson(errorAttributes));
    response.getWriter().flush();   }

    public String convertObjToJson(Object object) throws JsonProcessingException {
        if (object==null){
            return null;}
            ObjectMapper objectMapper=new ObjectMapper();
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);

    }


    public static Cookie getCookie(HttpServletRequest req,String name) {
        Cookie[] cookies = req.getCookies();
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }
}
