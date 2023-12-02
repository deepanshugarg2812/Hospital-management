package com.user.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main class of user management service
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.auth.layer", "com.user.management"})
public class UserManagement {
    public static void main(String[] args) {
        SpringApplication.run(UserManagement.class, args);
    }
}
