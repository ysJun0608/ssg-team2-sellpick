package com.ssg.wsmt.smorders.service.impl;

import com.ssg.wsmt.smOrders.domain.SmOrdersVo;
import com.ssg.wsmt.smOrders.dto.SmOrdersDTO;
import com.ssg.wsmt.smOrders.enums.SellerSendStatus;
import com.ssg.wsmt.smOrders.service.SmOrdersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SmOrderServiceImplTest {

    @Autowired
    SmOrdersService smOrderService;

    // 하나의 주문 조회
    @Test
    void getOneTest() {
        Long id = 4L;
        SmOrdersVo one = smOrderService.getOne(id);
        System.out.println(one.toString());
    }

    //주문 취소 리스트
    @Test
    void getCancelledListTest() {
        smOrderService.readAllCanceledOrders();
        System.out.println( "주문 취소 리스트 테스트 테스트 테스트 " +  smOrderService.readAllCanceledOrders());
    }

    //배송 준비중 리스트
    @Test
    void getPreparedListTest() {
        smOrderService.readAllPreparedOrders();
        System.out.println( "주문 취소 리스트 테스트 테스트 테스트 " +   smOrderService.readAllPreparedOrders());
    }

    // 업데이트 테스트
    @Test
    void updateStatusTest() {
        // 기존 주문 정보 조회를 위한 ID
        Long id = 4L;

        // 주문 상태 업데이트를 위한 DTO 생성 및 설정
        SmOrdersDTO dto = SmOrdersDTO.builder()
                .id(id)
                .seller_send_status(SellerSendStatus.CANCEL) // 예시로 주문 상태를 'CANCEL'로 설정
                .build();

        // 주문 상태 업데이트 실행
        smOrderService.updateStatus(dto);

        // 업데이트 후 결과 조회 및 출력
        SmOrdersVo updatedOrder = smOrderService.getOne(id);
        System.out.println("수정서비스 테스트 테스트 테스트 " +  updatedOrder.toString());
    }
    
    
    
}