package com.ssg.wsmt.inventory.mapper;

import com.ssg.wsmt.inventory.dto.InvenInOutChartDTO;
import com.ssg.wsmt.inventory.dto.WarehouseInsertReleaseDTO;
import com.ssg.wsmt.inventory.dto.WhPageRequestDTO;
import com.ssg.wsmt.inventory.enums.WhInOutType;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@SpringBootTest
class WarehouseInsertReleaseMapperTest {

    @Autowired
    private WarehouseInsertReleaseMapper warehouseInsertReleaseMapper;

    @Test
    void findWhInOutList() {
        // given
        WhPageRequestDTO whPageRequestDTO = WhPageRequestDTO.builder()
                .warehouseId(1l).build();

        // when
        List<WarehouseInsertReleaseDTO> allInsertReleaseList = warehouseInsertReleaseMapper.findAllInsertReleaseList(whPageRequestDTO);

        // then
        Assertions.assertNotNull(allInsertReleaseList);

        for (WarehouseInsertReleaseDTO dto : allInsertReleaseList) {
            log.info("warehouseInsertReleaseDTO = " + dto.toString());
        }
    }

    @Test
    void findWhInOutList2() {
        // given
        WhPageRequestDTO whPageRequestDTO = WhPageRequestDTO.builder()
                .page(2)
//                .keyword("티셔")
//                .type(WhInOutType.INSERT_REQUEST.toString())
//                .from(LocalDate.of(2020, 1, 1))
//                .to(LocalDate.of(2024, 3, 19))
                .warehouseId(2l)
                .build();

        // when
        List<WarehouseInsertReleaseDTO> allInsertReleaseList = warehouseInsertReleaseMapper.findAllInsertReleaseList(whPageRequestDTO);

        // then
        Assertions.assertNotNull(allInsertReleaseList);

        for (WarehouseInsertReleaseDTO dto : allInsertReleaseList) {
            log.info("warehouseInsertReleaseDTO = " + dto.toString());
        }
    }

    @Test
    void getCountTest() {
        // given
        WhPageRequestDTO whPageRequestDTO = WhPageRequestDTO.builder()
                .warehouseId(1L).build();

        // when
        int count = warehouseInsertReleaseMapper.getCount(whPageRequestDTO);

        // then
        Assertions.assertTrue(count > 0);
        log.info("count = " + count);
    }

    @Test
    void getAllSalesTest() {
        // when
        List<InvenInOutChartDTO> allSales = warehouseInsertReleaseMapper.getAllSales();

        // then
        Assertions.assertNotNull(allSales);

        for (InvenInOutChartDTO dto : allSales) {
            log.info("InvenInOutChartDTO = " + dto.toString());
        }
    }
}