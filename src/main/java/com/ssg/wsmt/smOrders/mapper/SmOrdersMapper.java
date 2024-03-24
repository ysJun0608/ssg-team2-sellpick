package com.ssg.wsmt.smOrders.mapper;

//import com.ssg.wsmt.smOrders.domain.SmOrdersVo;
import com.ssg.wsmt.smOrders.domain.SmOrdersVo;
import com.ssg.wsmt.smOrders.dto.SmOrdersDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SmOrdersMapper {
    List<SmOrdersVo> findAll();// 전체 조회
    List<SmOrdersVo> findCanceledOrders(); //취소건
    List<SmOrdersVo> findPreparedOrders();//배송 준비중인건
    void updateSmOrdersStatus(SmOrdersVo smOrdersVo); //주문 상태 update문

    SmOrdersVo selectOne(Long id); // 하나의 select

}