package com.example.projekatbioskop.controller;


import com.example.projekatbioskop.jwt.JwtRequest;
import com.example.projekatbioskop.jwt.JwtResponse;
import com.example.projekatbioskop.jwt.JwtTokenUtil;
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

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class LoginController {


    @GetMapping
    public String login(@AuthenticationPrincipal CustomUserDetails loggedUser){
        return "login";
    }



   // @GetMapping("/logout")
   // public String logout(){
    //    return "login-page";}




}
