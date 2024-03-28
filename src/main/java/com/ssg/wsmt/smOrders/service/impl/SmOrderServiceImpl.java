package com.ssg.wsmt.smOrders.service.impl;

import com.ssg.wsmt.smOrders.domain.SmOrdersVo;
import com.ssg.wsmt.smOrders.dto.PageRequestDTO;
import com.ssg.wsmt.smOrders.dto.PageResponseDTO;
import com.ssg.wsmt.smOrders.dto.SmOrdersDTO;
import com.ssg.wsmt.smOrders.enums.SellerSendStatus;
import com.ssg.wsmt.smOrders.mapper.SmOrdersMapper;
import com.ssg.wsmt.smOrders.service.SmOrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

import com.ssg.wsmt.smOrders.dto.PageRequestDTO;
import com.ssg.wsmt.smOrders.dto.PageResponseDTO;
import com.ssg.wsmt.smOrders.dto.SmOrdersDTO;
import com.ssg.wsmt.smOrders.mapper.SmOrdersMapper;
import com.ssg.wsmt.smOrders.service.SmOrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class SmOrderServiceImpl implements SmOrdersService {

    private final SmOrdersMapper smOrdersMapper;

    @Override
    public PageResponseDTO<SmOrdersDTO> ReadAlllist(PageRequestDTO pageRequestDTO) {
        int offset = pageRequestDTO.getOffset();
        int size = pageRequestDTO.getSize();

        List<SmOrdersDTO> dtoList = smOrdersMapper.searchAll(pageRequestDTO.getTypes(), pageRequestDTO.getKeyword(), offset, size);
        int totalCount = smOrdersMapper.getTotalCount(pageRequestDTO.getTypes(), pageRequestDTO.getKeyword());

        return PageResponseDTO.<SmOrdersDTO>withAll()
                .dtoList(dtoList)
                .page(pageRequestDTO.getPage()) // 페이지 정보 설정
                .size(pageRequestDTO.getSize()) // 페이지 크기 설정
                .total(totalCount) // 전체 항목 수 설정
                .build();
    }


    @Override
    public List<SmOrdersVo> readCompleteOrders(SmOrdersDTO smOrdersDTO) {
        return smOrdersMapper.findCompleteOrders();
    }

    @Override
    public List<SmOrdersVo> readAllCanceledOrders() {
        return smOrdersMapper.findCanceledOrders();
    }
    @Override
    public List<SmOrdersVo> readAllPreparedOrders() {
        return smOrdersMapper.findPreparedOrders();
    }
    @Override
    public void updateStatus(SmOrdersDTO smOrdersDTO) {
        // smOrdersVo 객체를 생성하여 필요한 필드 값을 설정
        SmOrdersVo smOrdersVo = SmOrdersVo.builder()
                .id(smOrdersDTO.getId())
                .seller_send_status(smOrdersDTO.getSeller_send_status())
                .customerId(smOrdersDTO.getCustomerId())
                .shoppingMallId(smOrdersDTO.getShoppingMallId())
                .productId(smOrdersDTO.getProductId())
                .build();

        // 주문 상태 업데이트를 위한 로그 출력
        log.info("업데이트 될 주문 정보:" + smOrdersVo);

        // 주문 상태 업데이트
        smOrdersMapper.updateSmOrdersStatus(smOrdersVo);

        // 업데이트 완료 로그 출력
        log.info("주문 상태가 업데이트 되었습니다.");
    }

    @Override
    public SmOrdersVo getOne(Long id) {
        return smOrdersMapper.selectOne(id);
    }

    @Override
    public List<SmOrdersDTO> searchOrders(Long orderId, Long customerId, SellerSendStatus sendStatus) {
        return smOrdersMapper.findOrdersByCriteria(orderId, customerId, sendStatus);
    }


}