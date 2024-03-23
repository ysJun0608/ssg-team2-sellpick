package com.ssg.wsmt.inventory.mapper;

import com.ssg.wsmt.product.domain.ProductVO;
import com.ssg.wsmt.product.enums.ProductsStatus;
import com.ssg.wsmt.product.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

public class ProductModifyTests {
    @Autowired
    private ProductMapper productMapper;
    @Test
    public void test(){
        productMapper.update(ProductVO.builder()
                .name("Updated Product")
                .status(ProductsStatus.STOP_SALE)
                .cost(15000)
                .price(25000)
                .brandId(2)
                .brandName("Updated Brand")
                .businessOwnerId(2L)
                .build());


    }
}
