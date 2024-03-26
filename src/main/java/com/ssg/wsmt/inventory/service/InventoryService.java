package com.ssg.wsmt.inventory.service;

import com.ssg.wsmt.inventory.domain.InventoryVO;
import com.ssg.wsmt.product.dto.ProductDTO;
import com.ssg.wsmt.product.dto.ProductDTO;

import java.util.List;

public interface InventoryService {
    List<ProductDTO> findAllProducts();
    ProductDTO findProductById(Long id);
    List<InventoryVO> findAllInventory();
    InventoryVO findInventoryById(Long id);
    List<InventoryVO> findInventoryByWarehouseId(Long warehouseId);
    List<InventoryVO> search(String keyword);
}
