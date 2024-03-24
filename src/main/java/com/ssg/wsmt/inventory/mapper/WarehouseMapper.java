package com.ssg.wsmt.inventory.mapper;

import com.ssg.wsmt.inventory.domain.WarehouseSectionVO;
import com.ssg.wsmt.inventory.domain.WarehouseVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WarehouseMapper {

    Long createWarehouse(WarehouseVO warehouseVO);
    Long saveWarehouseSection(WarehouseSectionVO section);
    List<WarehouseVO> readWarehouse();

    void updateDeliveryCmpId(WarehouseVO warehouseVO);
    void updateWarehouse(WarehouseVO warehouseVO);
    WarehouseVO findById(Long id);
}
