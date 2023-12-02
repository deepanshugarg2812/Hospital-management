package com.user.management.controller;

import com.auth.layer.AuthenticationLib.dto.request.LoginRequest;
import com.auth.layer.AuthenticationLib.dto.response.ResponseWrapper;
import com.user.management.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    @PostMapping("/login")
    public ResponseWrapper<String> login(@RequestBody LoginRequest loginRequest) {
        return new ResponseWrapper<>(200, loginService.loginUser(loginRequest));
    }
}
