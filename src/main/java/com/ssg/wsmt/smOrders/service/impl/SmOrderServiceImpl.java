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
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class SmOrderServiceImpl implements SmOrdersService {

    private final SmOrdersMapper smOrdersMapper;
    @Override
    public List<SmOrdersDTO> searchOrdersKeywordAll(Long orderId, Long customerId, SellerSendStatus sendStatus) {
        return smOrdersMapper.findOrdersByAll(orderId, customerId, sendStatus);
    }
    @Override
    public List<SmOrdersDTO> searchOrdersKeywordComplete(Long orderId, Long customerId, SellerSendStatus sendStatus) {
        return smOrdersMapper.findOrdersByComplete(orderId, customerId, sendStatus);
    }
    @Override
    public List<SmOrdersDTO> searchOrdersKeywordPrepare(Long orderId, Long customerId, SellerSendStatus sendStatus) {
        return smOrdersMapper.findOrdersByPrepare(orderId, customerId, sendStatus);
    }
    @Override
    public List<SmOrdersDTO> searchOrdersKeywordCancle(Long orderId, Long customerId, SellerSendStatus sendStatus) {
        return smOrdersMapper.findOrdersByCancle(orderId, customerId, sendStatus);
    }

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
    public PageResponseDTO<SmOrdersDTO> ReadCompletelist(PageRequestDTO pageRequestDTO) {
        int offset = pageRequestDTO.getOffset();
        int size = pageRequestDTO.getSize();

        List<SmOrdersDTO> dtoList = smOrdersMapper.searchComplete(pageRequestDTO.getTypes(), pageRequestDTO.getKeyword(), offset, size);
        int totalCount = smOrdersMapper.getTotalCount(pageRequestDTO.getTypes(), pageRequestDTO.getKeyword());

        return PageResponseDTO.<SmOrdersDTO>withAll()
                .dtoList(dtoList)
                .page(pageRequestDTO.getPage()) // 페이지 정보 설정
                .size(pageRequestDTO.getSize()) // 페이지 크기 설정
                .total(totalCount) // 전체 항목 수 설정
                .build();
    }

    @Override
    public PageResponseDTO<SmOrdersDTO> ReadPreparelist(PageRequestDTO pageRequestDTO) {
        int offset = pageRequestDTO.getOffset();
        int size = pageRequestDTO.getSize();

        List<SmOrdersDTO> dtoList = smOrdersMapper.searchPrepare(pageRequestDTO.getTypes(), pageRequestDTO.getKeyword(), offset, size);
        int totalCount = smOrdersMapper.getTotalCount(pageRequestDTO.getTypes(), pageRequestDTO.getKeyword());

        return PageResponseDTO.<SmOrdersDTO>withAll()
                .dtoList(dtoList)
                .page(pageRequestDTO.getPage()) // 페이지 정보 설정
                .size(pageRequestDTO.getSize()) // 페이지 크기 설정
                .total(totalCount) // 전체 항목 수 설정
                .build();
    }

    @Override
    public PageResponseDTO<SmOrdersDTO> ReadCanclelist(PageRequestDTO pageRequestDTO) {
        int offset = pageRequestDTO.getOffset();
        int size = pageRequestDTO.getSize();

        List<SmOrdersDTO> dtoList = smOrdersMapper.searchCancle(pageRequestDTO.getTypes(), pageRequestDTO.getKeyword(), offset, size);
        int totalCount = smOrdersMapper.getTotalCount(pageRequestDTO.getTypes(), pageRequestDTO.getKeyword());

        return PageResponseDTO.<SmOrdersDTO>withAll()
                .dtoList(dtoList)
                .page(pageRequestDTO.getPage()) // 페이지 정보 설정
                .size(pageRequestDTO.getSize()) // 페이지 크기 설정
                .total(totalCount) // 전체 항목 수 설정
                .build();
    }




    @Override
    public List<SmOrdersVo> findAllSmorders(SmOrdersDTO smOrdersDTO) {
//        SmOrdersVo smOrdersVo = SmOrdersVo.builder()
//                .id(smOrdersDTO.getId()) //
//                .build();
        return smOrdersMapper.findAll();
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




}