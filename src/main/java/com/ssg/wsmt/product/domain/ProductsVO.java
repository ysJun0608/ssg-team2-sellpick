package com.ssg.wsmt.product.domain;

import com.ssg.wsmt.product.enums.ProductsStatus;
<<<<<<<< HEAD:src/main/java/com/ssg/wsmt/product/domain/ProductVO.java
import lombok.*;

@ToString
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductVO {
========

import lombok.*;

@Data
@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductsVO {
>>>>>>>> origin/문지환2차:src/main/java/com/ssg/wsmt/product/domain/ProductsVO.java
    // 상품 id
    private Long id;
    // 상품 이름
    private String name;
    // 상품 판매 상태(판매 중 / 중지)
    private ProductsStatus status;
    // 원가
    private int cost;
<<<<<<<< HEAD:src/main/java/com/ssg/wsmt/product/domain/ProductVO.java
    // 판매가
    private int price;
========
    //수량
    private int quantity;
>>>>>>>> origin/문지환2차:src/main/java/com/ssg/wsmt/product/domain/ProductsVO.java
    // 브랜드 id
    private int brandId;
    private String brandName;
    // 사업자 id
    private Long businessOwnerId;

}
