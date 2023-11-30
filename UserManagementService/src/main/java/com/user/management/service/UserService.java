package com.user.management.service;

import com.user.management.dto.request.AddUserDto;

/**
 * Service to manage the user request
 */
public interface UserService {
    void addUser(AddUserDto addUserDto);
}
