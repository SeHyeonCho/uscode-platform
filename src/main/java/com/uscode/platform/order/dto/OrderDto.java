package com.uscode.platform.order.dto;

import lombok.Data;

@Data
public class OrderDto {
    private Long orderId;
    private String productName;
    private int quantity;
    private String username;
    private String number;
    private String address;

    public OrderDto(Long orderId, String productName, int quantity, String username, String number, String address) {
        this.orderId = orderId;
        this.productName = productName;
        this.quantity = quantity;
        this.username = username;
        this.number = number;
        this.address = address;
    }
}
