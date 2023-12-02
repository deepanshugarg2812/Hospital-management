package com.user.management.convertor.impl;

import com.user.management.convertor.CommonConvertor;
import com.user.management.dto.RoleDto;
import com.user.management.entity.Role;
import com.auth.layer.AuthenticationLib.exception.ApplicationRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RoleEntityToRoleDto implements CommonConvertor<Role, RoleDto> {
    @Override
    public Role convert(RoleDto roleDto) {
        return null;
    }

    @Override
    public RoleDto convertBack(Role role) {
        try {
            RoleDto roleDto = new RoleDto();
            BeanUtils.copyProperties(role, roleDto);
            return roleDto;
        } catch (Exception e) {
            throw new ApplicationRuntimeException("Error while transformation", e,
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
