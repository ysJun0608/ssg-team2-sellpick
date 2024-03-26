package com.ssg.wsmt.inventory.service.impl;

import com.ssg.wsmt.inventory.dto.PageRequestDTO;
import com.ssg.wsmt.inventory.service.WarehouseService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Log4j2
class WarehouseServiceImplTest {

    @Autowired
    private WarehouseService warehouseService;

    @Test
    public void testlist() {
        log.info( warehouseService.readAllWarehouse(PageRequestDTO.builder().build()));

    }

    @Test
    public void testWarehouseSection() {
        log.info(warehouseService.readAllWarehouseWithSection(PageRequestDTO.builder().build()));
    }


}