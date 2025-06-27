package com.uscode.platform.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCreateResponseDto {
    private String name;
    private String email;
}
