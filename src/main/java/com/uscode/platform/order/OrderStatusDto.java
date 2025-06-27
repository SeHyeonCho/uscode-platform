package com.uscode.platform.order;

import lombok.Data;

@Data
public class OrderStatusDto {

    private Long orderId;
    private OrderStatus status;
}
