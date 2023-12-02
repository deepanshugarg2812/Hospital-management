package com.auth.layer.AuthenticationLib.config;

import com.auth.layer.AuthenticationLib.filters.JwtTokenAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenAuthenticationProvider jwtTokenAuthenticationProvider;
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(jwtTokenAuthenticationProvider);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers( "/user/add").antMatchers("/login");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/user/add")
                        .authenticated().anyRequest().permitAll().and().
        addFilterBefore(jwtTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    private JwtTokenAuthenticationFilter jwtTokenAuthenticationFilter() throws Exception{
        JwtTokenAuthenticationFilter jwtTokenAuthenticationFilter =
                new JwtTokenAuthenticationFilter(new AntPathRequestMatcher("/**"),
                        authenticationManagerBean(), jwtSecret);
        return jwtTokenAuthenticationFilter;
    }


    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
