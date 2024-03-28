package com.ssg.wsmt.inventory.service;

import com.ssg.wsmt.inventory.dto.InventoryDTO;

import java.util.List;

public interface InventoryService {
    List<InventoryDTO> findAll();
    List<InventoryDTO> findByWarehouseId(Long warehouseId);
}
