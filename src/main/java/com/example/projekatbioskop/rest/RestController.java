package com.example.projekatbioskop.rest;

import com.example.projekatbioskop.security.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(path="/api")
public class RestController {

    @RequestMapping({ "/hello" })
    public String firstPage() {
        return "Hello World";
    }
}
