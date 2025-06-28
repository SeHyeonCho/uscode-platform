package com.uscode.platform.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginResponseDto {
    private String token;
    private String role;
    private String name;
    private Long userId;
}
