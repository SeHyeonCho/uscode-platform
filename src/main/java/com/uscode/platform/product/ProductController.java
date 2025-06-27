package com.uscode.platform.product;

import com.uscode.platform.product.dto.ProductCreateDto;
import com.uscode.platform.product.dto.ProductDetailDto;
import com.uscode.platform.product.dto.ProductListDto;
import com.uscode.platform.user.User;
import com.uscode.platform.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("api/products")
public class ProductController {

    private final ProductService productService;
    private final UserService userService;
    private final ImageService imageService;

    @GetMapping
    public ProductListDto getProductList() {
        List<Product> productList = productService.findAll();
        return new ProductListDto(productList);
    }

    @PostMapping
    public ResponseEntity<String> createProduct(
            @RequestPart("image") MultipartFile image,
            @RequestPart("userId") Long userId,
            @RequestPart("name") String name,
            @RequestPart("price") Long price,
            @RequestPart("description") String description
    ) {
        User user = userService.findById(userId);
        String path = imageService.saveImage(image);
        productService.save(Product.of(user, path, name, price, description));
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{productId}")
    public ProductDetailDto getProductDetail(@PathVariable Long productId) {
        Product product = productService.findById(productId);
        return new ProductDetailDto(product);
    }


}
