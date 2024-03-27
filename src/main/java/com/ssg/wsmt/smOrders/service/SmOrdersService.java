package com.ssg.wsmt.smOrders.service;


import com.ssg.wsmt.smOrders.domain.SmOrdersVo;
import com.ssg.wsmt.smOrders.dto.PageRequestDTO;
import com.ssg.wsmt.smOrders.dto.PageResponseDTO;
import com.ssg.wsmt.smOrders.dto.SmOrdersDTO;
import com.ssg.wsmt.smOrders.enums.SellerSendStatus;

import java.util.List;


public interface SmOrdersService {

    //전체 조회
    List<SmOrdersVo> findAllSmorders(SmOrdersDTO smOrdersDTO);
    List<SmOrdersVo> readCompleteOrders(SmOrdersDTO smOrdersDTO);

    //취소건 조회
    List<SmOrdersVo> readAllCanceledOrders();

    //배송중 조회
    List<SmOrdersVo> readAllPreparedOrders();

    //상태 enum 수정
    void updateStatus(SmOrdersDTO smOrdersDTO);

    //한건조회
    SmOrdersVo getOne(Long id);

    List<SmOrdersDTO> searchOrdersKeywordAll(Long orderId, Long customerId, SellerSendStatus status);
    List<SmOrdersDTO> searchOrdersKeywordComplete(Long orderId, Long customerId, SellerSendStatus status);

    List<SmOrdersDTO> searchOrdersKeywordPrepare(Long orderId, Long customerId, SellerSendStatus sendStatus);

    List<SmOrdersDTO> searchOrdersKeywordCancle(Long orderId, Long customerId, SellerSendStatus sendStatus);

    PageResponseDTO<SmOrdersDTO> ReadAlllist(PageRequestDTO requestDTO); // 전체건
    PageResponseDTO<SmOrdersDTO> ReadCompletelist(PageRequestDTO requestDTO); // 완료건
    PageResponseDTO<SmOrdersDTO> ReadPreparelist(PageRequestDTO requestDTO); // 취소건
    PageResponseDTO<SmOrdersDTO> ReadCanclelist(PageRequestDTO requestDTO); // 배송준비중인건

}
