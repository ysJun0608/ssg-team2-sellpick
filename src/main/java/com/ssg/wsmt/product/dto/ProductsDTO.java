package com.ssg.wsmt.product.dto;

import com.ssg.wsmt.product.enums.ProductsStatus;
import lombok.*;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductsDTO{
        Long id;
        // 상품 이름
        String name;        // 상품 판매 상태(판매 중 / 중지)
        ProductsStatus status;
        // 판매가
        int price;
        // 원가
        int cost;
        // 브랜드 id
        Long brandId;
        // 사업자 id
        Long businessOwnerId;
        // 브랜드 이름
        String brandName;
};