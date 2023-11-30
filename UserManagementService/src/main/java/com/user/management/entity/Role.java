package com.user.management.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Entity class for role
 */
@Entity
@Data
public class Role {
    @Id
    private Long roleId;
    private String roleName;
    private Date createdDate;
    private Date modifiedDate;
}
