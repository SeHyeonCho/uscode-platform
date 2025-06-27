package com.uscode.platform.user;

import com.uscode.platform.order.Order;
import com.uscode.platform.product.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    private boolean validate;

    private String name;
    private String email;
    private String password;
    private LocalDateTime localDateTime;

    @OneToMany(mappedBy = "user")
    private List<Product> productList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Order> orderList = new ArrayList<>();

    public static User of(String name, String email, String password) {
        User user = new User();
        user.name = name;
        user.email = email;
        user.password = password;
        return user;
    }

    public void validateUser() {
        validate = true;
    }

}
