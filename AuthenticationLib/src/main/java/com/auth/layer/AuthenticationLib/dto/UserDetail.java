package com.auth.layer.AuthenticationLib.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class UserDetail {
    private String token;
    private String userId;
    private String role;
}
