package com.uscode.platform.order;

import com.uscode.platform.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> getUserOrderList(Long userId) {
        return orderRepository.getOrderListByBuyerId(userId);
    }


    public List<Order> getSellerOrderList(Long userId) {
        return orderRepository.getOrderListBySellerId(userId);
    }


    public Order findById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(()->new RuntimeException("존재하지 않는 주문 입니다."));
    }

    public void acceptOrder(Long orderId) {
        Order order = findById(orderId);
        order.changeState(OrderStatus.CONFIRMED);
    }

    public void createOrder(Order order) {
        orderRepository.save(order);
    }


}
