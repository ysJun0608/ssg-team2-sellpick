package com.ssg.wsmt.smorders.mapper;

import com.ssg.wsmt.inventory.mapper.InventoryMapper;
import com.ssg.wsmt.smOrders.mapper.ShoppingMallMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShoppingMallMapperTest {

    @Autowired
    private ShoppingMallMapper shoppingMallMapper;


    @Test
    public void shopselectAll() throws Exception {
        System.out.println( "쇼핑몰 리스트!!!!!! : "+ shoppingMallMapper.selectAll());
    }
}