package com.uscode.platform.order.dto;

import com.uscode.platform.order.OrderStatus;
import lombok.Data;

@Data
public class OrderStatusDto {

    private Long orderId;
    private OrderStatus status;
}
