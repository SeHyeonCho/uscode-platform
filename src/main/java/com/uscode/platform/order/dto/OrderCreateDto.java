package com.uscode.platform.order.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OrderCreateDto {

    @NotNull
    private Long productId;

    @Min(value = 1)
    private int quantity;

    @NotNull
    @Positive
    private Long totalPrice;

    @NotBlank
    private String username;

    @NotBlank
    private String number;

    @NotBlank
    private String address;
}
