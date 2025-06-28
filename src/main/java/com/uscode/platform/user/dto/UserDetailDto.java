package com.uscode.platform.user.dto;

import com.uscode.platform.user.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDetailDto {

    @NotBlank
    private String name;

    @NotBlank
    private String number;

    @NotBlank
    private String address;

    public UserDetailDto() {
    }

    public UserDetailDto(User user) {
        this.name = user.getName();
        this.number = user.getNumber();
        this.address = user.getAddress();
    }
}
