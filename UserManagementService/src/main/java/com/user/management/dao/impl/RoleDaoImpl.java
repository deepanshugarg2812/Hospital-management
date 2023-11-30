package com.user.management.dao.impl;

import com.user.management.convertor.CommonConvertor;
import com.user.management.dao.RoleDao;
import com.user.management.dto.RoleDto;
import com.user.management.entity.Role;
import com.user.management.exception.ApplicationRuntimeException;
import com.user.management.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Role dao implementation
 */
@Component
@RequiredArgsConstructor
public class RoleDaoImpl implements RoleDao {
    private final RoleRepository roleRepository;
    private final CommonConvertor<Role, RoleDto> roleRoleDtoCommonConvertor;
    @Override
    public RoleDto getRoleIdForRole(String role) {
        try {
            Role roleDb = roleRepository.findByRoleName(StringUtils.upperCase(role));
            RoleDto roleDto = roleRoleDtoCommonConvertor.convertBack(roleDb);
            return roleDto;
        } catch (Exception e) {
            throw new ApplicationRuntimeException("Error while fetching the role", e,
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
