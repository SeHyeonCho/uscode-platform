package com.uscode.platform.product.repository;

import com.uscode.platform.product.Product;

import java.util.List;

public interface ProductQueryRepository {

    List<Product> findAllProductByUserId(Long userId);
}
