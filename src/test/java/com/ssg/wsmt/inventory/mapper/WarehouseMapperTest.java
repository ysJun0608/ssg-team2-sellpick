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

    @Test
    public void testRead() {



        log.info( warehouseMapper.readWarehouse(PageRequestDTO.builder().build()));

    }
}