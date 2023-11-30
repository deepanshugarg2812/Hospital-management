package com.user.management.convertor.impl;

import com.user.management.convertor.CommonConvertor;
import com.user.management.dao.RoleDao;
import com.user.management.dto.RoleDto;
import com.user.management.dto.UserDto;
import com.user.management.dto.request.AddUserDto;
import com.user.management.exception.ApplicationRuntimeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * Convertor class from adduserdto to userdto
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AddUserDtoToUserDto implements CommonConvertor<AddUserDto, UserDto> {
    private final RoleDao roleDao;
    @Override
    public AddUserDto convert(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto convertBack(AddUserDto addUserDto) {
        try {
            UserDto userDto = new UserDto();
            userDto.setId(UUID.randomUUID().toString())
                    .setAddress(addUserDto.getAddress())
                    .setCity(addUserDto.getCity())
                    .setEmail(addUserDto.getEmail())
                    .setAge(addUserDto.getAge())
                    .setCreatedDate(new Date())
                    .setModifiedDate(new Date())
                    .setUserId(addUserDto.getUsername())
                    .setPassword(addUserDto.getPassword())
                    .setName(addUserDto.getName())
                    .setState(addUserDto.getState())
                    .setIsActive(Boolean.TRUE);

            RoleDto roleDto = roleDao.getRoleIdForRole(addUserDto.getRole());
            if (Objects.isNull(roleDto)) {
                throw new ApplicationRuntimeException("Invalid role", HttpStatus.BAD_REQUEST.value());
            }
            userDto.setRoleId(roleDto.getRoleId());
            return userDto;
        } catch (ApplicationRuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationRuntimeException("Error while transformation", e, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
