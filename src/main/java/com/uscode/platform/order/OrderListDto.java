package com.uscode.platform.order;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrderListDto {

    List<OrderDto> orderList;

    public OrderListDto(List<Order> orderList) {
        this.orderList = orderList.stream().map(o ->
                new OrderDto(
                        o.getId(),
                        o.getProduct().getName(),
                        o.getQuantity(), o.getUser().getName(),
                        o.getUser().getNumber(),
                        o.getAddress()
                )).collect(Collectors.toList());
    }
}
