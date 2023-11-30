package com.user.management.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class UserDto implements Serializable {
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
