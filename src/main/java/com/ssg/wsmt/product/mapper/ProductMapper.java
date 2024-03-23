package com.ssg.wsmt.product.mapper;

import com.ssg.wsmt.product.domain.Products;
import com.ssg.wsmt.product.dto.ProductsOutput;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<ProductsOutput> productListInventory();
    void createProduct(Products product);
    void updateProduct(Products product);
}
