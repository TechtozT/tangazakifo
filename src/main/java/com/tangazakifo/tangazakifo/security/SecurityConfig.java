package com.tangazakifo.tangazakifo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final static String TOKEN_STRING = "token";
  private final static String COOKIE_STRING = "cookie";

  @Autowired
  private UserDetailsService userSvc;

  @Autowired
  private RememberMeAuthenticationProvider rememberMeProvider;

  @Autowired
  private CustomTokenBasedRememberMeService tokenSvc;

  @Autowired
  private AuthSuccessHandler authSuccess;

  @Autowired
  protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .userDetailsService(userSvc)
        .passwordEncoder(passwordEncoder());

    auth.authenticationProvider(rememberMeProvider);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http
        .authorizeRequests()
        .antMatchers("/register").permitAll().antMatchers("/login").permitAll()
        .anyRequest().authenticated().and()
        .formLogin()
        .loginPage("/login")
        .loginProcessingUrl("/login-process")
        .successHandler(authSuccess)
        .failureUrl("/login?err=1").and()
        .logout()
        .logoutUrl("/logout")
        .logoutSuccessUrl("/login")
        .deleteCookies(COOKIE_STRING).and()
        .rememberMe()
        .rememberMeServices(tokenSvc).and()
        .csrf()
        .disable()
        .addFilterBefore(rememberMeAuthenticationFilter(), BasicAuthenticationFilter.class)
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public RememberMeAuthenticationFilter rememberMeAuthenticationFilter() throws Exception {
    return new RememberMeAuthenticationFilter(authenticationManager(), tokenBasedRememberMeService());
  }

  @Bean
  public RememberMeAuthenticationProvider rememberMeAuthenticationProvider() {
    return new RememberMeAuthenticationProvider(TOKEN_STRING);
  }

  @Bean
  public CustomTokenBasedRememberMeService tokenBasedRememberMeService() {
    CustomTokenBasedRememberMeService service = new CustomTokenBasedRememberMeService(TOKEN_STRING,
        userSvc);
    service.setAlwaysRemember(true);
    service.setCookieName(COOKIE_STRING);
    return service;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    PasswordEncoder encoder = new BCryptPasswordEncoder();
    return encoder;
  }

}
