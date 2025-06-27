package com.uscode.platform.product.dto;


import lombok.Data;

@Data
public class ProductDto {

    private Long id;
    private String imgUrl;
    private String sellerName;
    private String name;
    private Long price;

    public ProductDto(Long id, String imgUrl, String sellerName, String name, Long price) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.sellerName = sellerName;
        this.name = name;
        this.price = price;
    }
}
