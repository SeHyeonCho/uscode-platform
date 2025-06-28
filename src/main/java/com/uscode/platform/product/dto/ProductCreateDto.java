package com.uscode.platform.product.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductCreateDto {

    private MultipartFile image;
    private Long userId;
    private String name;
    private Long price;
    private String description;
}
