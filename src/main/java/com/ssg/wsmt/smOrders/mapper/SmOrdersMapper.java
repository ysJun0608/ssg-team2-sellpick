package com.ssg.wsmt.smOrders.mapper;

import com.ssg.wsmt.smOrders.domain.SmOrdersVo;
import com.ssg.wsmt.smOrders.dto.SmOrdersDTO;
import com.ssg.wsmt.smOrders.enums.SellerSendStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface SmOrdersMapper {
    List<SmOrdersVo> findAll();// 전체 조회
    List<SmOrdersVo> findCompleteOrders(); //완료건
    List<SmOrdersVo> findCanceledOrders(); //취소건
    List<SmOrdersVo> findPreparedOrders();//배송 준비중인건
    void updateSmOrdersStatus(SmOrdersVo smOrdersVo); //주문 상태 update문

    SmOrdersVo selectOne(Long id); // 하나의 select
    List<SmOrdersDTO> findOrdersByCriteria( Long orderId , Long customerId, SellerSendStatus sendStatus); //임시주석

    List<SmOrdersDTO> searchAll(@Param("types") String[] types, @Param("keyword") String keyword, @Param("offset") int offset, @Param("size") int size);
    int getTotalCount(@Param("types") String[] types, @Param("keyword") String keyword);
}