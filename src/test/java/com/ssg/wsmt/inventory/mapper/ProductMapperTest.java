//package com.ssg.wsmt.inventory.mapper;
//
//import com.ssg.wsmt.product.domain.ProductsVO;
//import com.ssg.wsmt.product.enums.ProductsStatus;
//import com.ssg.wsmt.product.mapper.ProductMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//public class ProductMapperTest {
//    @Autowired
//    private ProductMapper productMapper;
//
//    @Test
//    public void insertTest(){
//
//        ProductsStatus productsStatus = ProductsStatus.ON_SALE;
//
//        ProductsVO productsVO = ProductsVO.builder()
//                .name("구민석")
//                .status(productsStatus)
//                        .cost(1)
//                                .price(1)
//                                        .brandId(1L)
//                                                .businessOwnerId(1L)
//                .build();
//        productMapper.insert(productsVO);
//    }
//}
