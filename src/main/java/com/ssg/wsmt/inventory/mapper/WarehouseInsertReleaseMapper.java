package com.ssg.wsmt.inventory.mapper;

import com.ssg.wsmt.inventory.domain.WarehouseInsertReleaseVO;
import com.ssg.wsmt.inventory.dto.PageRequestDTO;
import com.ssg.wsmt.inventory.dto.WarehouseInOutDTO;
import com.ssg.wsmt.inventory.dto.WarehouseInsertReleaseDTO;
import com.ssg.wsmt.inventory.dto.WhPageRequestDTO;
import com.ssg.wsmt.inventory.enums.WhInOutType;
import com.ssg.wsmt.mgtOrders.MgtOrdersProductsRelationshipDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface WarehouseInsertReleaseMapper {
//    List<MgtOrdersProductsRelationshipDTO> findAllInsertProducts();
//    void updateInsertStatus(WarehouseInOutDTO warehouseInOutDTO);
//    WarehouseInsertReleaseVO warehouserelease(WarehouseVO warehouseVO);
//    void updateWhInOutStatus(Long id, WhInOutType whInOutType);
//    ArrayList<WarehouseInOutDTO> findAllWhInOutList(WhInOutType whInOutType);


    List<WarehouseInsertReleaseDTO> findAllInsertReleaseList(WhPageRequestDTO pageRequestDTO);
    int getCount(WhPageRequestDTO pageRequestDTO);
}
