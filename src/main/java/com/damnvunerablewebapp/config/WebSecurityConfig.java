package com.damnvunerablewebapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers( "/public/**").permitAll()
                    .antMatchers("/", "/home").permitAll()
                    .antMatchers("/admin").hasRole("ADMIN")
                    .anyRequest().authenticated()
                .and()
                .formLogin().successHandler(customizeAuthenticationSuccessHandler)
                    .loginPage("/login")
                    .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .csrf().ignoringAntMatchers("/console/**")
                .and()
                .headers()
                .frameOptions().sameOrigin();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        
        auth
                .inMemoryAuthentication()
                .withUser("user1").password("user1").roles("USER")
                .and()
                .withUser("user2").password("user2").roles("USER")
                .and()
                .withUser("admin1").password("admin1").roles("ADMIN")
                .and()
                .withUser("admin2").password("admin2").roles("ADMIN")
                .and()
                .withUser("user3").password("user3").roles("USER");
    }
}
