package com.example.projekatbioskop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
//@AuthenticationPrincipal CustomUserDetails loggedUser
    @GetMapping
    public String login(){
        return "login";
    }



    @GetMapping("/logout")
    public String logout(){
        return "login-page";
    }
}
