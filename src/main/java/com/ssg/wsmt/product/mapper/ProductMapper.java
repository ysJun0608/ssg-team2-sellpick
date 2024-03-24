package com.ssg.wsmt.product.mapper;

import com.ssg.wsmt.product.domain.ProductsVO;
import com.ssg.wsmt.product.dto.ProductsDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<ProductsDTO> productListInventory();
    void insert(ProductsVO productsVO);
    void update(ProductsVO productsVO);
}
