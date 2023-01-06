package com.example.projekatbioskop.controller;


import com.example.projekatbioskop.security.RegistrationForm;
import com.example.projekatbioskop.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")

public class RegistrationController {
    @Autowired
    UserServiceImpl userDetailsService;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(UserServiceImpl userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
   public String processRegistration(RegistrationForm form) {
     userDetailsService.save(form.toUser(passwordEncoder));
      return "redirect:/login";
   }

}
