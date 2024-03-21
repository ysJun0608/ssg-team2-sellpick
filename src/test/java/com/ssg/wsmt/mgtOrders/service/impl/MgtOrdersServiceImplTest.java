package com.ssg.wsmt.mgtOrders.service.impl;

import com.ssg.wsmt.mgtOrders.DTO.MgtOrdersDTO;
import com.ssg.wsmt.mgtOrders.domain.MgtOrders;
import com.ssg.wsmt.mgtOrders.enums.MgtOrdersStatus;
import com.ssg.wsmt.mgtOrders.mapper.MgtOrdersMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class MgtOrdersServiceImplTest {

    @Autowired
    MgtOrdersServiceImpl mgtOrdersService;

    @Autowired
    MgtOrdersMapper mgtOrdersMapper;

    @Test
    void add() {

//        LocalDateTime localDateTime = LocalDateTime.now();
//        //given
//        MgtOrders mgtOrders = MgtOrders.builder()
//                .purchaser("Test")
//                .status(MgtOrdersStatus.READY)
//                .createdAt(localDateTime)
//                .warehouseId(1L)
//                .build();
//
//        //when
//        mgtOrdersMapper.createOrder(mgtOrders);
        MgtOrders testObj = mgtOrdersMapper.getOne(1L);

        //then
        Assertions.assertThat(testObj).isNotNull();
        Assertions.assertThat(testObj.getId()).isEqualTo(1L);
        Assertions.assertThat(testObj.getPurchaser()).isEqualTo("Supplier A");
        Assertions.assertThat(testObj.getStatus()).isEqualTo(MgtOrdersStatus.READY);
//        Assertions.assertThat(testObj.getCreatedAt()).isEqualTo("2024-03-20 18:27:58");
//        Assertions.assertThat(testObj.getWarehouseId()).isEqualTo(1l);
    }

    @Test
    void getAllOrders() {
    }

    @Test
    void confirmOrder() {
    }

    @Test
    void cancelOrder() {
    }

    @Test
    void confirmList() {
    }

    @Test
    void searchNonDelivered() {
    }

    @Test
    void confirmArrival() {
    }

    @Test
    void delete() {
    }
}