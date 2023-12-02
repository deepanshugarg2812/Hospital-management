package com.auth.layer.AuthenticationLib.config;

import com.auth.layer.AuthenticationLib.dto.JwtTokenAuthentication;
import com.auth.layer.AuthenticationLib.dto.UserDetail;
import com.auth.layer.AuthenticationLib.exception.ApplicationRuntimeException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenAuthenticationProvider implements AuthenticationProvider {
    private final RedisTemplate<String, String> redisTemplate;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            UserDetail userDetail = (UserDetail) authentication.getPrincipal();
            String token = redisTemplate.opsForValue().get(userDetail.getUserId());
            if (StringUtils.equals(token, userDetail.getToken())){
                return new UsernamePasswordAuthenticationToken(userDetail, token, authentication.getAuthorities());
            } else {
                throw new BadCredentialsException("Invalid credentials");
            }
        } catch (ApplicationRuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid credentials");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtTokenAuthentication.class.equals(authentication);
    }
}
