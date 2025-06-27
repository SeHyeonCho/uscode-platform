package com.uscode.platform.user.dto;

import com.uscode.platform.user.User;
import lombok.Data;

@Data
public class UserDetailDto {
    private String name;
    private String number;
    private String address;

    public UserDetailDto(User user) {
        this.name = user.getName();
        this.number = user.getNumber();
        this.address = user.getAddress();
    }
}
