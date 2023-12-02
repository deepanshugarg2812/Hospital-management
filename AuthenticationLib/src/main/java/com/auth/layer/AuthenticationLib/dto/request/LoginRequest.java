package com.auth.layer.AuthenticationLib.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.io.Serializable;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginRequest implements Serializable {
    private static final long serialVersionUID = -3340771677688867012L;
    private String userName;
    private String password;
}
