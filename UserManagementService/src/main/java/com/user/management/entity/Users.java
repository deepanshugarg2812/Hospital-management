package com.user.management.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Entity class for user
 */
@Entity
@Data
public class Users {
    @Id
    private String id;
    private String name;
    private String address;
    private String city;
    private String state;
    private Integer age;
    private String email;
    private Boolean isActive;
    private Date createdDate;
    private Date modifiedDate;
    private Long roleId;
    private String userId;
    private String password;
}
