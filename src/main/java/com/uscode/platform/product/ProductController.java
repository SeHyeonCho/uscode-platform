package com.uscode.platform.product;

import com.uscode.platform.ai.AiService;
import com.uscode.platform.ai.dto.ImageGradeDto;
import com.uscode.platform.product.dto.ProductCreateDto;
import com.uscode.platform.product.dto.ProductDetailDto;
import com.uscode.platform.product.dto.ProductListDto;
import com.uscode.platform.user.User;
import com.uscode.platform.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("api/products")
public class ProductController {

    private final ProductService productService;
    private final UserService userService;
    private final ImageService imageService;
    private final AiService aiService;

    @GetMapping
    public ProductListDto getProductList() {
        List<Product> productList = productService.findAll();
        return new ProductListDto(productList);
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@ModelAttribute @Valid ProductCreateDto dto) {
        User user = userService.findById(dto.getUserId());
        String path = imageService.saveImage(dto.getImage());
        String filename = path.substring(8);
        log.info("PATH = [{}]", path);
        log.info("FILENAME = [{}]", filename);
        ImageGradeDto imageClassify = aiService.getImageClassify(filename);
        ProductGrade grade = imageService.grading(imageClassify);
        productService.save(Product.of(user, path, dto.getName(), dto.getPrice(), dto.getDescription(), grade));
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{productId}")
    public ProductDetailDto getProductDetail(@PathVariable Long productId) {
        Product product = productService.findById(productId);
        return new ProductDetailDto(product);
    }
}
