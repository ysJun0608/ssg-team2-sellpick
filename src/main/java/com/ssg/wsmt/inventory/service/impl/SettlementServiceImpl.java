package com.ssg.wsmt.inventory.service.impl;

import com.ssg.wsmt.inventory.dto.PageRequestDTO;
import com.ssg.wsmt.inventory.dto.PageResponseDTO;
import com.ssg.wsmt.inventory.dto.SettlementChartDTO;
import com.ssg.wsmt.inventory.dto.SettlementDTO;
import com.ssg.wsmt.inventory.mapper.SettlementMapper;
import com.ssg.wsmt.inventory.service.SettlementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class SettlementServiceImpl implements SettlementService {

    private final SettlementMapper settlementMapper;

    @Override
    public PageResponseDTO<SettlementDTO> ReadAllSetlist(PageRequestDTO pageRequestDTO) {
        int offset = pageRequestDTO.getOffset();
        int size = pageRequestDTO.getSize();

        List<SettlementDTO> dtoList = settlementMapper.searchSetAll(pageRequestDTO.getTypes(), pageRequestDTO.getKeyword(), offset, size);
        int totalCount = settlementMapper.getSetTotalCount(pageRequestDTO.getTypes(), pageRequestDTO.getKeyword());

        return PageResponseDTO.<SettlementDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
//                .page(pageRequestDTO.getPage()) // 페이지 정보 설정
//                .size(pageRequestDTO.getSize()) // 페이지 크기 설정
                .total(totalCount) // 전체 항목 수 설정
                .build();
    }

    @Override
    public List<SettlementChartDTO> getChartData() {
        return settlementMapper.getChartData();
    }

    @Override
    public List<SettlementDTO> searchSet(Long settlementId) {
        return settlementMapper.findSetByCriteria(settlementId);
    }

    @Override
    public List<SettlementDTO> setDateList(LocalDate startDate, LocalDate endDate) {
        return settlementMapper.selectSetDate(startDate, endDate);
    }
}
