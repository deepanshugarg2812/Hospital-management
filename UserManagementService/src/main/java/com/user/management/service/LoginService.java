package com.user.management.service;

import com.auth.layer.AuthenticationLib.dto.request.LoginRequest;

/**
 * Service is used to handle the login and logout request
 */
public interface LoginService {
    /**
     * Method is used to manage the login request
     * @param loginRequest
     * @return
     */
    String loginUser(LoginRequest loginRequest);
}
