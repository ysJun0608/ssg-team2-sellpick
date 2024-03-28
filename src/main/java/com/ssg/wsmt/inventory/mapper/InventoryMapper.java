package com.ssg.wsmt.inventory.mapper;

import com.ssg.wsmt.inventory.dto.InventoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InventoryMapper {
    List<InventoryDTO> findAll();
    List<InventoryDTO> findByWarehouseId(Long warehouseId);
}

