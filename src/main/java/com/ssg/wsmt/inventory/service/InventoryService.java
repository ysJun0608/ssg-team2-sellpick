package com.ssg.wsmt.inventory.service;

import com.ssg.wsmt.inventory.domain.InventoryVO;
import com.ssg.wsmt.inventory.dto.InventoryDTO;
import com.ssg.wsmt.inventory.dto.PageRequestDTO;
import com.ssg.wsmt.inventory.dto.PageResponseDTO;
import com.ssg.wsmt.inventory.enums.WhSectionType;
import com.ssg.wsmt.product.dto.ProductDTO;
import com.ssg.wsmt.product.dto.ProductDTO;

import java.util.List;

public interface InventoryService {
    List<InventoryDTO> findAll();
    List<InventoryDTO> findByWarehouseId(Long warehouseId);
    InventoryVO getOne(Long id);

    List<InventoryDTO> searchOrders(Long warehouseId);
    PageResponseDTO<InventoryDTO> list(PageRequestDTO pageRequestDTO);

}
