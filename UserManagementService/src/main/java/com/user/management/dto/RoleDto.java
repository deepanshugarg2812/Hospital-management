package com.user.management.dto;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;


@Data
public class RoleDto implements Serializable {
    private static final long serialVersionUID = 4714548375614710707L;
    private Long roleId;
    private String roleName;
}
