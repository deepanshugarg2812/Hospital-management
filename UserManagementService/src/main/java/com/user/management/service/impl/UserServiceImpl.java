package com.user.management.service.impl;

import com.user.management.convertor.CommonConvertor;
import com.user.management.dao.UserDao;
import com.user.management.dto.UserDto;
import com.user.management.dto.request.AddUserDto;
import com.user.management.exception.ApplicationRuntimeException;
import com.user.management.service.UserService;
import com.user.management.validator.AddUserRequestValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Impl class for Users service
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final CommonConvertor<AddUserDto, UserDto> addUserDtoUserDtoCommonConvertor;
    private final UserDao userDao;
    private final AddUserRequestValidator addUserRequestValidator;
    @Override
    public void addUser(AddUserDto addUserDto) {
        try {
            log.info("Request came to add new user {}", addUserDto);
            addUserRequestValidator.validate(addUserDto, null);
            UserDto userDto = addUserDtoUserDtoCommonConvertor.convertBack(addUserDto);
            userDao.addUser(userDto);
            return;
        } catch (ApplicationRuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationRuntimeException("Error occured while adding user", e,
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
