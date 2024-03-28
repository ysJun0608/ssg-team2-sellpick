package com.ssg.wsmt.inventory.service.impl;


import com.ssg.wsmt.inventory.dto.InvenInOutChartDTO;
import com.ssg.wsmt.inventory.dto.WarehouseInsertReleaseDTO;
import com.ssg.wsmt.inventory.dto.WhPageRequestDTO;
import com.ssg.wsmt.inventory.dto.WhPageResponseDTO;
import com.ssg.wsmt.inventory.mapper.WarehouseInsertReleaseMapper;
import com.ssg.wsmt.inventory.service.WarehouseInsertReleaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class WarehouseInsertReleaseServiceImpl implements WarehouseInsertReleaseService {

    private final WarehouseInsertReleaseMapper warehouseInOutMapper;
    @Override
    public WhPageResponseDTO<WarehouseInsertReleaseDTO> findAllInsertReleaseList(Long warehouseId, WhPageRequestDTO pageRequestDTO) {
        pageRequestDTO.setWarehouseId(warehouseId);
        List<WarehouseInsertReleaseDTO> list = warehouseInOutMapper.findAllInsertReleaseList(pageRequestDTO);

        int total = warehouseInOutMapper.getCount(pageRequestDTO);

        WhPageResponseDTO<WarehouseInsertReleaseDTO> dtoList = WhPageResponseDTO.<WarehouseInsertReleaseDTO>withAll()
                .dtoList(list)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();

        return dtoList;
    }

    @Override
    public List<InvenInOutChartDTO> getAllSales() {
        return warehouseInOutMapper.getAllSales();
    }

    @Override
    public List<WarehouseInsertReleaseDTO> recentInsertReleaseList() {
        return warehouseInOutMapper.recentInsertReleaseList();
    }

    @Override
    public Long findTodayInsertCost(WarehouseInsertReleaseDTO warehouseInsertReleaseDTO) {
        return warehouseInOutMapper.getTotalCost(warehouseInsertReleaseDTO);
    }

    @Override
    public Long findTodayReleasePrice(WarehouseInsertReleaseDTO warehouseInsertReleaseDTO) {
        return warehouseInOutMapper.getTotalPrice(warehouseInsertReleaseDTO);
    }

    @Override
    public Long findTodayRevenue(WarehouseInsertReleaseDTO warehouseInsertReleaseDTO) {
        return warehouseInOutMapper.getTotalRevenue(warehouseInsertReleaseDTO);
    }
}

