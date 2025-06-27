package com.uscode.platform.product.dto;

import lombok.Data;

@Data
public class ProductCreateDto {

    private String imgUrl;
    private String name;
    private Long price;
    private String content;
}
