package com.user.management.dao.impl;

import com.user.management.convertor.CommonConvertor;
import com.user.management.dao.UserDao;
import com.user.management.dto.UserDto;
import com.user.management.entity.Users;
import com.user.management.exception.ApplicationRuntimeException;
import com.user.management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Implementation for Users Dao
 */
@Component
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    private final CommonConvertor<UserDto, Users> userDtoUserCommonConvertor;
    private final UserRepository userRepository;
    @Override
    public void addUser(UserDto userDto) {
        try {
            Users users = userDtoUserCommonConvertor.convertBack(userDto);
            userRepository.save(users);
        } catch (Exception e) {
            throw new ApplicationRuntimeException("Error while saving the user", e,
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
