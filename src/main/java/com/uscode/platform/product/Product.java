package com.uscode.platform.product;

import com.uscode.platform.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public static Product of(User user, String imgUrl, String name, Long price, String description, ProductGrade grade) {
        Product product = new Product();
        product.user = user;
        product.imgUrl = imgUrl;
        product.name = name;
        product.price = price;
        product.description = description;
        product.grade = grade;
        return product;
    }

    public void assignGrade(ProductGrade grade) {
        this.grade = grade;
    }




}
