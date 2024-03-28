package com.ssg.wsmt.smOrders.mapper;

//import com.ssg.wsmt.smOrders.domain.SmOrdersVo;
import com.ssg.wsmt.smOrders.domain.SmOrdersVo;
import com.ssg.wsmt.smOrders.dto.PageRequestDTO;
import com.ssg.wsmt.smOrders.dto.PageResponseDTO;
import com.ssg.wsmt.smOrders.dto.SmOrdersDTO;
import com.ssg.wsmt.smOrders.enums.SellerSendStatus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
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