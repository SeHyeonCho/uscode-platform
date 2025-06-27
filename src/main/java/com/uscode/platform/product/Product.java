package com.uscode.platform.product;

import com.uscode.platform.user.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Product {

    @Id @GeneratedValue
    @Column(name = "product_id")
    private Long id;

    private String name;
    private String description;
    private Long price;
    private Long stock;
    private String imgUrl;

    @Enumerated(EnumType.STRING)
    private ProductGrade grade;
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;




}
