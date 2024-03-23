package com.ssg.wsmt;

import com.ssg.wsmt.mgtOrders.DTO.MgtOrdersDTO;
import com.ssg.wsmt.mgtOrders.domain.MgtOrders;
import com.ssg.wsmt.mgtOrders.enums.MgtOrdersStatus;
import com.ssg.wsmt.mgtOrders.mapper.MgtOrdersMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class WsmtApplicationTests {

    @Autowired
    MgtOrdersMapper mgtOrdersMapper;
    @Test
    void addItem() {
        LocalDateTime localDateTime = LocalDateTime.now();
        MgtOrders mgtOrders = MgtOrders.builder()
                .id(1L)
                .purchaser("Test")
                .status(MgtOrdersStatus.READY)
                .warehouseId(1L)
                .build();

        mgtOrdersMapper.createOrder(mgtOrders);

        MgtOrders mgtorders = mgtOrdersMapper.getOne(1L);

        Assertions.assertThat(mgtorders.getId()).isEqualTo(1L);
        Assertions.assertThat(mgtorders.getPurchaser()).isEqualTo("Test");
        Assertions.assertThat(mgtorders.getStatus()).isEqualTo(MgtOrdersStatus.READY);
        Assertions.assertThat(mgtorders.getCreatedAt()).isEqualTo(localDateTime);
        Assertions.assertThat(mgtorders.getWarehouseId()).isEqualTo(1L);
    }

}
