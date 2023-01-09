package com.example.projekatbioskop.controller;

import com.example.projekatbioskop.security.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
//
    @GetMapping
    public String login(@AuthenticationPrincipal CustomUserDetails loggedUser){
        return "login";
    }



   // @GetMapping("/logout")
   // public String logout(){
    //    return "login-page";}

}
