package com.uscode.platform.order.dto;

import com.uscode.platform.order.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderStatusDto {


    @NotNull
    private Long orderId;

    @NotNull
    private OrderStatus status;
}
