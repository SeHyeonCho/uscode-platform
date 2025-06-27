package com.uscode.platform.user.dto;

import lombok.Data;

@Data
public class UserCreateDto {
    private String name;
    private String email;
    private String password;

    public UserCreateDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
