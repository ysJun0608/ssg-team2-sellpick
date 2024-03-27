package com.ssg.wsmt.inventory.mapper;

import com.ssg.wsmt.inventory.domain.SettlementVO;
import com.ssg.wsmt.inventory.dto.SettlementChartDTO;
import org.apache.ibatis.annotations.Mapper;

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

    // 정산 검색
    SettlementVO search();

    List<SettlementChartDTO> getChartData();
}
