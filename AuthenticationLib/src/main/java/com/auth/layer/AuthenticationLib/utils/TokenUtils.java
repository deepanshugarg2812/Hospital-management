package com.auth.layer.AuthenticationLib.utils;

import com.auth.layer.AuthenticationLib.dto.UserDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class TokenUtils {
    public UserDetail parseToken(String token, String secret) {
        try {
            Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            UserDetail userDetail = new UserDetail();
            userDetail.setUserId((String) body.get("userId"));
            userDetail.setRole((String) body.get("role"));
            userDetail.setToken(token);
            return userDetail;
        } catch (AuthenticationServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("Invalid user");
        }
    }

    public String generateToken(UserDetail userDetail, String jwtSecret) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userDetail.getUserId());
        claims.put("role", userDetail.getRole());
        return doGenerateToken(claims, jwtSecret);
    }

    private String doGenerateToken(Map<String, Object> claims, String jwtSecret) {
        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }
}
