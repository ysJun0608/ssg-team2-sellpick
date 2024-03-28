package com.ssg.wsmt.product.dto;

import com.ssg.wsmt.product.enums.ProductsStatus;
import lombok.*;


@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private ProductsStatus status;
    private int cost;
    private int price;
    private int brandId;
    private Long businessOwnerId;
    private String brandName;
}