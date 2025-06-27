package com.uscode.platform.product;

import com.uscode.platform.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Product {

    @Id @GeneratedValue
    @Column(name = "product_id")
    private Long id;

    private String name;
    private String title;
    private String description;
    private Long price;
    private Long stock;
    private String img_url;
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;




}
