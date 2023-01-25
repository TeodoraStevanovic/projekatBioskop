package com.example.projekatbioskop.security;

  import com.example.projekatbioskop.jwt.JwtAuthenticationEntryPoint;
    import com.example.projekatbioskop.jwt.JwtRequestFilter;
    import com.example.projekatbioskop.service.UserDetailServiceImpl;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
  import org.springframework.http.HttpMethod;
  import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
    import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
    import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
    import org.springframework.security.config.http.SessionCreationPolicy;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

  import javax.servlet.http.Cookie;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailServiceImpl();
    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler(){return new LoginSuccessHandler();}
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();}

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder);}
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;}

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
                .antMatchers("/register","/").permitAll()
                .antMatchers("/authform").permitAll()
                .antMatchers("/api/auth","/api/registration").permitAll()
                .antMatchers("/film/**","/bioskop/**","/projekcija/**","/sala/**","/home").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.GET,"/authform").hasAnyAuthority("USER", "ADMIN")
                //hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated().and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/?logout").addLogoutHandler((request, response, auth) -> {
                    for (Cookie cookie : request.getCookies()) {
                        String cookieName = cookie.getName();
                        Cookie cookieToDelete = new Cookie(cookieName, null);
                        cookieToDelete.setMaxAge(0);
                        response.addCookie(cookieToDelete);
                    }
                }).and()
                .exceptionHandling().accessDeniedPage("/access-denied").
                authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
//kod za custom login page Spring Security
    ////  .and()
    //                //.formLogin()
    //               // .loginPage("/login")
    //               // .usernameParameter("username")
    //               // .passwordParameter("password")
    //              //  .loginProcessingUrl("/login")
    //              //  .successHandler(loginSuccessHandler())
    //               // .permitAll()
//ovo  mi sad ne treba vise
    // .antMatchers(HttpMethod.POST,"/authenticate").permitAll()


}

