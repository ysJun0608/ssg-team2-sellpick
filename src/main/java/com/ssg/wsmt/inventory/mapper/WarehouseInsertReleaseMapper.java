package com.ssg.wsmt.inventory.mapper;

import com.ssg.wsmt.inventory.dto.WarehouseInOutDTO;
import com.ssg.wsmt.inventory.enums.WhInOutType;
import com.ssg.wsmt.mgtOrders.MgtOrdersProductsRelationshipDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface WarehouseInsertReleaseMapper {
    List<MgtOrdersProductsRelationshipDTO> findAllInsertProducts();
//    void updateInsertStatus(WarehouseInOutDTO warehouseInOutDTO);
//    WarehouseInsertReleaseVO warehouserelease(WarehouseVO warehouseVO);
    void updateWhInOutStatus(Long id, WhInOutType whInOutType);
//    ArrayList<WarehouseInOutDTO> findAllWhInOutList(WhInOutType whInOutType);
}
