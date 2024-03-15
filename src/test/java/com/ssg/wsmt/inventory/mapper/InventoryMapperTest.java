package com.ssg.wsmt.inventory.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InventoryMapperTest {

    @Autowired
    private InventoryMapper inventoryMapper;

    @Test
    public void test() throws Exception {
        System.out.println(inventoryMapper.getTime());
    }
    @Test
    public void test2() throws Exception {
        System.out.println(inventoryMapper.selectAll());
    }


}