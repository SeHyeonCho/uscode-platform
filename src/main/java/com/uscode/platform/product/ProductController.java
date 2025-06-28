package com.uscode.platform.product;

import com.uscode.platform.product.dto.ProductCreateDto;
import com.uscode.platform.product.dto.ProductDetailDto;
import com.uscode.platform.product.dto.ProductListDto;
import com.uscode.platform.user.User;
import com.uscode.platform.user.UserService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
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
    public ResponseEntity<String> createProduct(@ModelAttribute @Valid ProductCreateDto dto) {
        User user = userService.findById(dto.getUserId());
        String path = imageService.saveImage(dto.getImage());
        productService.save(Product.of(user, path, dto.getName(), dto.getPrice(), dto.getDescription()));
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{productId}")
    public ProductDetailDto getProductDetail(@PathVariable Long productId) {
        Product product = productService.findById(productId);
        return new ProductDetailDto(product);
    }


}
