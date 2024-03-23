package com.ssg.wsmt.inventory.service;

import com.ssg.wsmt.inventory.domain.InventoryVO;
import com.ssg.wsmt.product.dto.ProductsDTO;

import java.util.List;

public interface InventoryService {
    List<ProductsDTO> findAllProducts();
    ProductsDTO findProductById(Long id);
    List<InventoryVO> findAllInventory();
    InventoryVO findInventoryById(Long id);
    List<InventoryVO> findInventoryByWarehouseId(Long warehouseId);
    List<InventoryVO> search(String keyword);
}
