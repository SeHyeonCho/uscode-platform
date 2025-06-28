package com.uscode.platform.product.dto;

import com.uscode.platform.product.Product;
import com.uscode.platform.product.ProductGrade;
import lombok.Data;

@Data
public class ProductDetailDto {
    private String imgUrl;
    private String sellerName;
    private String name;
    private Long price;
    private ProductGrade grade;
    private String description;

    public ProductDetailDto(Product product) {
        this.imgUrl = product.getImgUrl();
        this.sellerName = product.getUser().getName();
        this.name = product.getName();
        this.price = product.getPrice();
        this.grade = product.getGrade();
        this.description = product.getDescription();
    }
}
