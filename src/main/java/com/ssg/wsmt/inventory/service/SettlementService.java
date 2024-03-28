package com.ssg.wsmt.inventory.service;

import com.ssg.wsmt.inventory.dto.PageRequestDTO;
import com.ssg.wsmt.inventory.dto.PageResponseDTO;
import com.ssg.wsmt.inventory.dto.SettlementChartDTO;
import com.ssg.wsmt.inventory.dto.SettlementDTO;
import com.ssg.wsmt.smOrders.dto.SmOrdersDTO;
import com.ssg.wsmt.smOrders.enums.SellerSendStatus;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface SettlementService {
    // 정산 리스트 출력
////    void settlementList();
//    List<SettlementDTO> settlementList(); 임시주석

    List<SettlementChartDTO> getChartData();

    // 정산 리스트 기간별 조회
    List<SettlementDTO> setDateList(LocalDate startDate, LocalDate endDate);

    //페이징 처리
    List<SettlementDTO> searchSet(Long settlementId);
    PageResponseDTO<SettlementDTO> ReadAllSetlist(PageRequestDTO requestDTO);
//
//    List<SettlementDTO> searchOrders(Long settlementId);
//    PageResponseDTO<SettlementDTO> ReadAlllist(PageRequestDTO requestDTO);
}