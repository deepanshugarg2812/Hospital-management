package com.user.management.dao;

import com.auth.layer.AuthenticationLib.dto.UserDetail;
import com.user.management.dto.UserDto;

/**
 * Dao layer for user entity
 */
public interface UserDao {
    /**
     * Method is used to add user
     * @param userDto
     */
    void addUser(UserDto userDto);

    /**
     * Method to fetch the users id
     * @param userName
     * @param password
     * @return
     */
    UserDetail fetchUser(String userName, String password);
}
