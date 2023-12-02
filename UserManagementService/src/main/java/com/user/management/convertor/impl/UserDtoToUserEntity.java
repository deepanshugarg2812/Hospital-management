package com.user.management.convertor.impl;

import com.user.management.convertor.CommonConvertor;
import com.user.management.dto.UserDto;
import com.user.management.entity.Users;
import com.auth.layer.AuthenticationLib.exception.ApplicationRuntimeException;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToUserEntity implements CommonConvertor<UserDto, Users> {
    @Override
    public UserDto convert(Users users) {
        return null;
    }

    @Override
    public Users convertBack(UserDto userDto) {
        try {
            Users users = new Users();
            BeanUtils.copyProperties(userDto, users);
            return users;
        } catch (Exception e) {
            throw new ApplicationRuntimeException("Error while transformation", e,
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
