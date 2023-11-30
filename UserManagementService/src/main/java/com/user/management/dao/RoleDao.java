package com.user.management.dao;

import com.user.management.dto.RoleDto;

/**
 * Dao layer for role entity
 */
public interface RoleDao {
    RoleDto getRoleIdForRole(String role);
}
