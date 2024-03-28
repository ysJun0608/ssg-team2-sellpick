package com.ssg.wsmt.inventory.service;

import com.ssg.wsmt.inventory.dto.SettlementDTO;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SettlementService {
    // 정산 리스트 출력
//    void settlementList();
    List<SettlementDTO> settlementList();



    // 특정 날짜 정산 출력
   // void settlementByDate();
}
