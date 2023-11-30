package com.user.management.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Getter;

import java.io.Serializable;

/**
 * Addition of user dto
 */
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddUserDto implements Serializable {
    private static final long serialVersionUID = -3601180030340749449L;
    private String name;
    private String address;
    private String city;
    private String state;
    private Integer age;
    private String role;
    private String email;
    private String username;
    private String password;
}
