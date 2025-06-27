package com.uscode.platform.order.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.uscode.platform.order.Order;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.uscode.platform.order.QOrder.*;

public class OrderQueryRepositoryImpl implements OrderQueryRepository {

    private final JPAQueryFactory query;

    public OrderQueryRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<Order> getOrderListByBuyerId(Long userId) {
        return query.selectFrom(order)
                .where(order.user.id.eq(userId))
                .fetch();
    }

    @Override
    public List<Order> getOrderListBySellerId(Long userId) {
        return query.selectFrom(order)
                .where(order.product.user.id.eq(userId))
                .fetch();
    }
}
