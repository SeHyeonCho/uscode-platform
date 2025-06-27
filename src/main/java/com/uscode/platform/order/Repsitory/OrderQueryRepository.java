package com.uscode.platform.order.Repsitory;

import com.uscode.platform.order.Order;

import java.util.List;

public interface OrderQueryRepository {
    List<Order> getOrderListByBuyerId(Long userId);

    List<Order> getOrderListBySellerId(Long userId);
}
