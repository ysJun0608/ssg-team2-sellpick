package com.ssg.wsmt.product.service;

import com.ssg.wsmt.product.dto.ProductDTO;
import com.ssg.wsmt.product.enums.ProductsStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductsServiceTest {
    @Autowired
    private ProductService productsService;

    @Test
    public void testRegister(){
        ProductsStatus productsStatus = ProductsStatus.ON_SALE;
        ProductDTO productDTO = ProductDTO.builder()
                .name("팔민석")
                .status(productsStatus)
                .cost(1)
                .price(1)
                .brandId(1)
                .businessOwnerId(1L).build();
        productsService.register(productDTO);
    }
}
