package com.user.management.controller;

import com.auth.layer.AuthenticationLib.dto.response.ResponseWrapper;
import com.user.management.dto.request.AddUserDto;
import com.user.management.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Users Controller
 */
@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * Api to add users
     * @param addUserDto
     * @return
     */
    @PostMapping(value = "/add")
    public ResponseWrapper<Object> addUser(@RequestBody AddUserDto addUserDto) {
        userService.addUser(addUserDto);
        return new ResponseWrapper<>(HttpStatus.OK.value(), "Success");
    }
}
