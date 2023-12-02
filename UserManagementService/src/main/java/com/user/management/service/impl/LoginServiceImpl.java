package com.user.management.service.impl;

import com.auth.layer.AuthenticationLib.dto.UserDetail;
import com.auth.layer.AuthenticationLib.dto.request.LoginRequest;
import com.auth.layer.AuthenticationLib.exception.ApplicationRuntimeException;
import com.auth.layer.AuthenticationLib.utils.TokenUtils;
import com.user.management.dao.UserDao;
import com.user.management.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final UserDao userDao;
    private final RedisTemplate<String, String> redisTemplate;
    @Value("${jwt.secret}")
    private String secret;

    @Override
    public String loginUser(LoginRequest loginRequest) {
        try {
            UserDetail user = userDao.fetchUser(loginRequest.getUserName(), loginRequest.getPassword());
            String token = TokenUtils.generateToken(user, secret);
            redisTemplate.opsForValue().set(user.getUserId(), token);
            return token;
        } catch (ApplicationRuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationRuntimeException("Error occurred while login user", e,
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
