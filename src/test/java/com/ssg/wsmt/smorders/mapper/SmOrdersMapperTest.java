package com.ssg.wsmt.smorders.mapper;

import com.ssg.wsmt.smOrders.dto.SmOrdersDTO;
import com.ssg.wsmt.smOrders.enums.SellerSendStatus;
import com.ssg.wsmt.smOrders.mapper.ShoppingMallMapper;
import com.ssg.wsmt.smOrders.mapper.SmOrdersMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SmOrdersMapperTest {
    @Autowired
    private SmOrdersMapper smOrdersMapper;

    @Test
    public void selectOrders() throws Exception {
        System.out.println( "배송 취소 list 출력 테스트  :"+ smOrdersMapper.selectOne(1L));
    }
    @Test
    public void canceledOrders() throws Exception {
        System.out.println( "배송 취소 list 출력 테스트  :"+ smOrdersMapper.findCanceledOrders());
    }

    @Test
    public void preparedOrders() throws Exception {
        System.out.println( "배송준비중 list 출력 테스트 :  "+ smOrdersMapper.findPreparedOrders());
    }

    @Test
    public void s() throws Exception {
        System.out.println( "전체 주문 list 출력 테스트 :  "+ smOrdersMapper.findAll());
    }

}