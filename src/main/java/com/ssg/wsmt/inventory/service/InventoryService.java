package com.ssg.wsmt.inventory.service;

import com.ssg.wsmt.inventory.domain.InventoryVO;
import com.ssg.wsmt.inventory.dto.InventoryDTO;
import com.ssg.wsmt.product.dto.ProductDTO;
import com.ssg.wsmt.product.dto.ProductDTO;

import java.util.List;

public interface InventoryService {
    List<InventoryDTO> findAll();
    List<InventoryDTO> findByWarehouseId(Long warehouseId);

}
