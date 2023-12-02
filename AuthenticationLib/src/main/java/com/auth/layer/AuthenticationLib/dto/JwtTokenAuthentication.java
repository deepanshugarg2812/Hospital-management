package com.auth.layer.AuthenticationLib.dto;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

public class JwtTokenAuthentication extends UsernamePasswordAuthenticationToken {
    public JwtTokenAuthentication(UserDetail userDetail, String token, List<GrantedAuthority> authorities) {
        super(userDetail, token, authorities);
    }
}
