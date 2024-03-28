package com.ssg.wsmt.inventory.mapper;

import com.ssg.wsmt.inventory.dto.InvenInOutChartDTO;
import com.ssg.wsmt.inventory.dto.WarehouseInsertReleaseDTO;
import com.ssg.wsmt.inventory.dto.WhPageRequestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WarehouseInsertReleaseMapper {

    List<WarehouseInsertReleaseDTO> findAllInsertReleaseList(WhPageRequestDTO pageRequestDTO);
    int getCount(WhPageRequestDTO pageRequestDTO);

    List<InvenInOutChartDTO> getAllSales();

    List<WarehouseInsertReleaseDTO> recentInsertReleaseList();

    Long getTotalPrice(WarehouseInsertReleaseDTO warehouseInsertReleaseDTO);

    Long getTotalCost(WarehouseInsertReleaseDTO warehouseInsertReleaseDTO);

    Long getTotalRevenue(WarehouseInsertReleaseDTO warehouseInsertReleaseDTO);
}
