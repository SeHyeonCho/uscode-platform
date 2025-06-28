package com.uscode.platform.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductCreateDto {


    @NotNull
    private MultipartFile image;

    @NotNull
    private Long userId;

    @NotBlank
    private String name;

    @NotNull
    @Positive
    private Long price;

    @NotBlank
    private String description;
}
