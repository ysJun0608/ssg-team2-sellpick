package com.ssg.wsmt.inventory.mapper;

import com.ssg.wsmt.inventory.dto.PageRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Log4j2
class WarehouseMapperTest {
    @Autowired
    private WarehouseMapper warehouseMapper;
//    @Test
//    public void test() {
//        Assertions.assertThat(all).isNotNull();
//        List<InventoryVO> all = inventoryMapper.findAll();
//    }
//
//    @Test
//    public void testRead() {
//        List<InventoryVO> mapperAll = inventoryMapper.findAll();
//    }

    @Test
    public void test2(){
            log.info( warehouseMapper.readWarehouse(PageRequestDTO.builder().build()));

    }

    @Test
    public void testReadWarehouseWithSection() {
        log.info(warehouseMapper.readWarehouseSection(PageRequestDTO.builder().build()));
    }

    @Test
    public void testgetcount() {
        log.info(warehouseMapper.getTotalCount(PageRequestDTO.builder().build()));
    }
}