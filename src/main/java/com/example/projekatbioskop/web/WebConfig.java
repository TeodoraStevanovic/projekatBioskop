package com.example.projekatbioskop.web;

import com.example.projekatbioskop.interceptor.BearerTokenInterceptor;
import com.example.projekatbioskop.interceptor.BearerTokenWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
       // registry.addViewController("/").setViewName("homepage");
       // registry.addViewController("/index").setViewName("homepage");
        registry.addViewController("/access-denied").setViewName("access-denied");
      //  registry.addViewController("/admin").setViewName("admin");

    }

   // private final long MAX_AGE_SECS = 3600;

   // @Value("${app.cors.allowedOrigins}")
   // private String[] allowedOrigins;

  //  @Override
   // public void addCorsMappings(CorsRegistry registry) {
       // registry.addMapping("/**")
              //  .allowedOrigins(allowedOrigins)
              //  .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
               // .maxAge(MAX_AGE_SECS);}

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // register the interceptor
        registry.addInterceptor(bearerTokenInterceptor());
        // you can exclude certain URL patterns here, for example
        // .excludePathPatterns("/health")
    }

    // the 2 methods below produces the bean for token wrapper and interceptor in request scope

    @Bean
    public BearerTokenInterceptor bearerTokenInterceptor() {
        return new BearerTokenInterceptor(bearerTokenWrapper());
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public BearerTokenWrapper bearerTokenWrapper() {
        return new BearerTokenWrapper();
    }
}
