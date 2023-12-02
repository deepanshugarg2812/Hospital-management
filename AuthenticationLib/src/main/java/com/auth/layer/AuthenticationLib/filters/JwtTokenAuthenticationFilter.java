package com.auth.layer.AuthenticationLib.filters;

import com.auth.layer.AuthenticationLib.dto.JwtTokenAuthentication;
import com.auth.layer.AuthenticationLib.dto.UserDetail;
import com.auth.layer.AuthenticationLib.utils.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Objects;


public class JwtTokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private String jwtSecret;
    public JwtTokenAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher,
                                           AuthenticationManager authenticationManager, String jwtSecret) {
        super(requiresAuthenticationRequestMatcher, authenticationManager);
        this.jwtSecret = jwtSecret;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        try {
            String token = request.getHeader("Authorization");
            if (StringUtils.isBlank(token) || !token.startsWith("Bearer ")) {
                throw new AuthenticationServiceException("Unauthorized");
            }

            String jwt = token.substring(7);
            UserDetail userDetail = null;
            userDetail = TokenUtils.parseToken(jwt, jwtSecret);
            if (Objects.isNull(userDetail) || StringUtils.isAnyBlank(userDetail.getUserId(),
                    userDetail.getToken(), userDetail.getRole())) {
                throw new AuthenticationServiceException("Unauthorized");
            }

            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userDetail.getRole());
            JwtTokenAuthentication jwtTokenAuthentication = new JwtTokenAuthentication(
                    userDetail,
                    jwt,
                    Collections.singletonList(grantedAuthority)
            );

            return getAuthenticationManager().authenticate(jwtTokenAuthentication);
        } catch (AuthenticationServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new AuthenticationServiceException(e.getMessage());
        }
    }
}
