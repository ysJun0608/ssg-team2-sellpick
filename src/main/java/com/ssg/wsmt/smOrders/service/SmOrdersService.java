package com.ssg.wsmt.smOrders.service;


import com.ssg.wsmt.smOrders.domain.SmOrdersVo;
import com.ssg.wsmt.smOrders.dto.PageRequestDTO;
import com.ssg.wsmt.smOrders.dto.PageResponseDTO;
import com.ssg.wsmt.smOrders.dto.SmOrdersDTO;
import com.ssg.wsmt.smOrders.enums.SellerSendStatus;

import java.util.List;


public interface SmOrdersService {

    List<SmOrdersVo> readCompleteOrders(SmOrdersDTO smOrdersDTO);

    //취소건 조회
    List<SmOrdersVo> readAllCanceledOrders();

    //배송중 조회
    List<SmOrdersVo> readAllPreparedOrders();

    //상태 enum 수정
    void updateStatus(SmOrdersDTO smOrdersDTO);

    //한건조회
    SmOrdersVo getOne(Long id);

    List<SmOrdersDTO> searchOrders(Long orderId, Long customerId, SellerSendStatus status);
    PageResponseDTO<SmOrdersDTO> ReadAlllist(PageRequestDTO requestDTO);

}
