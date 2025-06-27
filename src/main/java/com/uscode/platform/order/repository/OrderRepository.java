package com.uscode.platform.order.repository;

import com.uscode.platform.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderQueryRepository {

}
