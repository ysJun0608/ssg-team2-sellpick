package com.ssg.wsmt.inventory.service;

import com.ssg.wsmt.inventory.dto.InvenInOutChartDTO;
import com.ssg.wsmt.inventory.dto.WarehouseInsertReleaseDTO;
import com.ssg.wsmt.inventory.dto.WhPageRequestDTO;
import com.ssg.wsmt.inventory.dto.WhPageResponseDTO;

import java.util.List;

public interface WarehouseInsertReleaseService {
    WhPageResponseDTO<WarehouseInsertReleaseDTO> findAllInsertReleaseList(Long warehouseId, WhPageRequestDTO pageRequestDTO);

    List<InvenInOutChartDTO> getAllSales();

    List<WarehouseInsertReleaseDTO> recentInsertReleaseList();
    Long findTodayInsertCost(WarehouseInsertReleaseDTO warehouseInsertReleaseDTO);

    Long findTodayReleasePrice(WarehouseInsertReleaseDTO warehouseInsertReleaseDTO);

    Long findTodayRevenue(WarehouseInsertReleaseDTO warehouseInsertReleaseDTO);

}
