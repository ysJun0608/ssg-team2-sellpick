package com.ssg.wsmt.inventory.mapper;

import com.ssg.wsmt.inventory.dto.WarehouseInsertReleaseDTO;
import com.ssg.wsmt.inventory.dto.WhPageRequestDTO;
import org.apache.ibatis.annotations.Mapper;

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
