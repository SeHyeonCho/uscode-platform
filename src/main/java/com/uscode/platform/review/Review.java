package com.uscode.platform.review;

import com.uscode.platform.product.Product;
import com.uscode.platform.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Review {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    private Long rating;
    private String content;
    private LocalDateTime created_at;
}
