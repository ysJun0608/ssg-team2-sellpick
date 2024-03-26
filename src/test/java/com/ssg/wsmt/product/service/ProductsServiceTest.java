package com.ssg.wsmt.product.service;

import com.ssg.wsmt.product.dto.ProductsDTO;
import com.ssg.wsmt.product.enums.ProductsStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductsServiceTest {
    @Autowired
    private ProductsService productsService;

    @Test
    public void testRegister(){
        ProductsStatus productsStatus = ProductsStatus.ON_SALE;
        ProductsDTO productsDTO = ProductsDTO.builder()
                .name("팔민석")
                .status(productsStatus)
                .cost(1)
                .price(1)
                .brandId(1L)
                .businessOwnerId(1L).build();
        productsService.register(productsDTO);
    }
}
