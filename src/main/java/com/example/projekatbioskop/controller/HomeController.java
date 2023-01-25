package com.example.projekatbioskop.controller;

import com.example.projekatbioskop.interceptor.BearerTokenWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    //ne treba mi ovde tokenWrapper al cisto sam probala da li radi
    private BearerTokenWrapper tokenWrapper;

    public HomeController(BearerTokenWrapper tokenWrapper) {
        this.tokenWrapper = tokenWrapper;
    }

    @RequestMapping("/home")

    public String auth() {

        return "homepage";
    }


}
