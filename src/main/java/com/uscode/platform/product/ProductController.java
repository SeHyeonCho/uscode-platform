package com.uscode.platform.product;

import com.uscode.platform.product.dto.ProductDetailDto;
import com.uscode.platform.product.dto.ProductListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ProductListDto getProductList() {
        List<Product> productList = productService.findAll();
        return new ProductListDto(productList);
    }

    @GetMapping("/{productId}")
    public ProductDetailDto getProductDetail(@PathVariable Long productId) {
        Product product = productService.findById(productId);
        return new ProductDetailDto(product);
    }
}
