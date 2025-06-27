package com.uscode.platform.product.dto;


import com.uscode.platform.product.Product;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProductListDto {
    List<ProductDto> productList;

    public ProductListDto(List<Product> productList) {
       this.productList = productList.stream().map(p -> new ProductDto(p.getId(), p.getImgUrl(), p.getUser().getName(), p.getName(), p.getPrice())).collect(Collectors.toList());
    }


}
