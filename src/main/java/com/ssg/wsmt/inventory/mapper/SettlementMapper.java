package com.ssg.wsmt.inventory.mapper;

import com.ssg.wsmt.inventory.domain.SettlementVO;
import com.ssg.wsmt.inventory.dto.PageRequestDTO;
import com.ssg.wsmt.inventory.dto.SettlementChartDTO;
import com.ssg.wsmt.inventory.dto.SettlementDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface SettlementMapper {

    // 정산 조회
    SettlementVO select(Long id);

    // 정산 리스트 전체 조회
    List<SettlementVO> selectAll();

    // 정산 수정
    void update(SettlementVO settlementVO);

    // 정산 삭제
    void delete(Long id);

    int getTotalCount(PageRequestDTO pageRequestDTO);
    // 정산 검색
    SettlementVO search();

    // 정산 리스트 기간별 조회
    List<SettlementDTO> selectSetDate(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    //    페이징 처리
    List<SettlementDTO> findSetByCriteria(Long settlementId); //임시주석

    List<SettlementDTO> searchSetAll(@org.apache.ibatis.annotations.Param("types") String[] types, @org.apache.ibatis.annotations.Param("keyword") String keyword, @org.apache.ibatis.annotations.Param("offset") int offset, @org.apache.ibatis.annotations.Param("size") int size);
    int getSetTotalCount(@org.apache.ibatis.annotations.Param("types") String[] types, @org.apache.ibatis.annotations.Param("keyword") String keyword);

    List<SettlementChartDTO> getChartData();
}