package com.uscode.platform.order;

import com.uscode.platform.product.Product;
import com.uscode.platform.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;
    private Long totalPrice;
    private String address;

    private LocalDateTime orderedDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime createdAt;

    public void changeState(OrderStatus status) {
        this.status = status;
    }

    public static Order of(Product product, User user, int quantity, Long totalPrice, String address) {
        Order order = new Order();
        order.product = product;
        order.user = user;
        order.quantity = quantity;
        order.totalPrice = totalPrice;
        order.address = address;
        order.status = OrderStatus.PAID;
        return order;
    }
}

