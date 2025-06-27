package com.uscode.platform.order.dto;

import lombok.Data;

@Data
public class OrderCreateDto {
    private Long productId;
    private int quantity;
    private Long totalPrice;
    private String username;
    private String number;
    private String address;
}
