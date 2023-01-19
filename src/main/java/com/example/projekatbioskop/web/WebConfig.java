package com.example.projekatbioskop.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("homepage");
        registry.addViewController("/index").setViewName("homepage");
        registry.addViewController("/access-denied").setViewName("access-denied");
      //  registry.addViewController("/admin").setViewName("admin");

    }
}
