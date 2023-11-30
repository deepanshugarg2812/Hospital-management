package com.user.management.validator;

import com.user.management.dto.request.AddUserDto;
import com.user.management.exception.ApplicationRuntimeException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
public class AddUserRequestValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return AddUserRequestValidator.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (Objects.isNull(target)) {
            throw new ApplicationRuntimeException("Invalid request", HttpStatus.BAD_REQUEST.value());
        }
        AddUserDto addUserDto = (AddUserDto) target;
        if (StringUtils.isBlank(addUserDto.getAddress())) {
            throw new ApplicationRuntimeException("Address is mandatory", HttpStatus.BAD_REQUEST.value());
        }
        if (StringUtils.isBlank(addUserDto.getEmail())) {
            throw new ApplicationRuntimeException("Email is mandatory", HttpStatus.BAD_REQUEST.value());
        }
        if (StringUtils.isBlank(addUserDto.getCity())) {
            throw new ApplicationRuntimeException("City is mandatory", HttpStatus.BAD_REQUEST.value());
        }
        if (StringUtils.isBlank(addUserDto.getName())) {
            throw new ApplicationRuntimeException("Name is mandatory", HttpStatus.BAD_REQUEST.value());
        }
        if (StringUtils.isBlank(addUserDto.getPassword())) {
            throw new ApplicationRuntimeException("Password is mandatory", HttpStatus.BAD_REQUEST.value());
        }
        if (StringUtils.isBlank(addUserDto.getRole())) {
            throw new ApplicationRuntimeException("Role is mandatory", HttpStatus.BAD_REQUEST.value());
        }
        if (StringUtils.isBlank(addUserDto.getUsername())) {
            throw new ApplicationRuntimeException("Username is mandatory", HttpStatus.BAD_REQUEST.value());
        }
        if (StringUtils.isBlank(addUserDto.getState())) {
            throw new ApplicationRuntimeException("State is mandatory", HttpStatus.BAD_REQUEST.value());
        }
    }
}
