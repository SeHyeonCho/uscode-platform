package com.uscode.platform.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserCreateDto {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    public UserCreateDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
