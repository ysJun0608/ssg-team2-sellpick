package com.ssg.wsmt.inventory.service.impl;

import com.ssg.wsmt.inventory.dto.WhPageRequestDTO;
import com.ssg.wsmt.inventory.service.WarehouseInsertReleaseService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@SpringBootTest
class WarehouseInsertReleaseServiceImplTest {

    @Autowired
    private WarehouseInsertReleaseService warehouseInsertReleaseService;

    @Test
    void findAllInsertReleaseList() {
        // given
        WhPageRequestDTO whPageRequestDTO = WhPageRequestDTO.builder().build();

        // when
        log.info(warehouseInsertReleaseService.findAllInsertReleaseList(1L, whPageRequestDTO));
    }

    @Test
    void getAllSales() {
        // when
        log.info(warehouseInsertReleaseService.getAllSales());
    }
}