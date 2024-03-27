package com.ssg.wsmt.mgtOrders.service.impl;

import com.ssg.wsmt.mgtOrders.DTO.MgtOrdersDTO;
import com.ssg.wsmt.mgtOrders.domain.MgtOrders;
import com.ssg.wsmt.mgtOrders.enums.MgtOrdersStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MgtOrdersServiceImplTest {

    @Autowired
    MgtOrdersServiceImpl mgtOrdersService;

    @Test
    public void add() {
        MgtOrdersDTO mgtOrders = MgtOrdersDTO.builder()
                .purchaser("test")
                .status(MgtOrdersStatus.READY)
                .warehouseId(1L)
                .build();

        System.out.println(mgtOrdersService.createForm(mgtOrders));
    }

}