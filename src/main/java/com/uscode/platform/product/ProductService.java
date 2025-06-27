package com.uscode.platform.product;

import com.uscode.platform.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findAllByUserId(Long userId) {
        return productRepository.findAllProductByUserId(userId);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(()-> new RuntimeException("존재하지 않는 상품 입니다."));
    }

    public void save(Product product) {

    }





}
