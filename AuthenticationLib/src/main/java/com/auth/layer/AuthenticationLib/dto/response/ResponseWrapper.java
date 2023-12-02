package com.auth.layer.AuthenticationLib.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Response wrapper
 */
@Getter
@Setter
@AllArgsConstructor
public class ResponseWrapper<T> implements Serializable {
    private int status;
    private T result;
}
