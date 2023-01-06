package com.example.projekatbioskop.security;

import com.example.projekatbioskop.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailServiceImpl();
    }
 //   @Bean
  //  public PasswordEncoder passwordEncoder() {
  //      return new BCryptPasswordEncoder();
  //  }

 @Bean
 public LoginSuccessHandler loginSuccessHandler(){
     return new LoginSuccessHandler();
 }
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return authenticationProvider;
    }

//radi ali to je bez baze
  //  @Override
   // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //    auth.inMemoryAuthentication()
        //        .passwordEncoder(passwordEncoder)
        //        .withUser("admin").password(passwordEncoder.encode("123456")).roles("ADMIN").authorities("ADMIN")
       //         .and()
       //         .withUser("user").password(passwordEncoder.encode("123456")).roles("USER").authorities("USER");

    //    auth.authenticationProvider(authenticationProvider()); }


   @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/login","/register","/").permitAll()
                .antMatchers("/film/**","/bioskop/**","/projekcija/**","/sala/**","/").hasAnyAuthority("USER", "ADMIN")
                //hasAnyAuthority("USER", "ADMIN")
                //hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(loginSuccessHandler)
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login");;

    }

}
