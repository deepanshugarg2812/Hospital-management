package com.user.management.dao;

import com.user.management.dto.UserDto;

/**
 * Dao layer for user entity
 */
public interface UserDao {
    void addUser(UserDto userDto);
}
