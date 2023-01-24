package com.example.projekatbioskop.controller;

import com.example.projekatbioskop.interceptor.BearerTokenWrapper;
import org.aspectj.apache.bcel.classfile.Constant;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    private BearerTokenWrapper tokenWrapper;

    public HomeController(BearerTokenWrapper tokenWrapper) {
        this.tokenWrapper = tokenWrapper;
    }

    @RequestMapping("/home")

    public String auth() {

        return "homepage";
    }


}
