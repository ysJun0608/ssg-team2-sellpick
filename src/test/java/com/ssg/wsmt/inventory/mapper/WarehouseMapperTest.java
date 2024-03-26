//package com.ssg.wsmt.inventory.mapper;
//
//import com.ssg.wsmt.inventory.domain.InventoryVO;
//import com.ssg.wsmt.inventory.dto.PageRequestDTO;
//import lombok.extern.log4j.Log4j2;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//import static org.apache.ibatis.ognl.DynamicSubscript.all;
//import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest
//@Log4j2
//class WarehouseMapperTest {
//    @Autowired
//    private WarehouseMapper warehouseMapper;
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
//
//    @Test
//    public void test2(){
//            log.info( warehouseMapper.readWarehouse(PageRequestDTO.builder().build()));
//
//    }
//}