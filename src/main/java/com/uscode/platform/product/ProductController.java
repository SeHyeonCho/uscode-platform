package com.uscode.platform.product;

import com.uscode.platform.product.dto.ProductCreateDto;
import com.uscode.platform.product.dto.ProductDetailDto;
import com.uscode.platform.product.dto.ProductListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody ProductCreateDto dto) {
        productService.save(Product.of(dto.getImgUrl(), dto.getName(), dto.getPrice(), dto.getContent()));
        return ResponseEntity.ok("상품 등록 성공");
    }

    @GetMapping("/{productId}")
    public ProductDetailDto getProductDetail(@PathVariable Long productId) {
        Product product = productService.findById(productId);
        return new ProductDetailDto(product);
    }


}
