package com.uscode.platform.user;

import com.uscode.platform.order.Order;
import com.uscode.platform.product.Product;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String name;
    private String email;
    private String password;
    private LocalDateTime localDateTime;

    @OneToMany(mappedBy = "user")
    private List<Product> productList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Order> orderList = new ArrayList<>();

}
