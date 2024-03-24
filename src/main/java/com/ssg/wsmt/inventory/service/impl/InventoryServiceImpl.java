package com.ssg.wsmt.inventory.service.impl;

import com.ssg.wsmt.inventory.domain.InventoryVO;
import com.ssg.wsmt.inventory.mapper.InventoryMapper;
import com.ssg.wsmt.inventory.service.InventoryService;
import com.ssg.wsmt.product.dto.ProductsDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@Log4j2
public class InventoryServiceImpl implements InventoryService {
    private final InventoryMapper inventoryMapper;

    @Override
    public List<ProductsDTO> findAllProducts() {
        return inventoryMapper.findAllProducts();
    }

    @Override
    public ProductsDTO findProductById(Long id) {
        return inventoryMapper.findProductById(id);
    }

    public void searchInventory() {
//        System.out.println("찾고자 하는 재고의 id를 입력해주세요 : ");
//        Long id = Long.parseLong(sc.nextLine());
//        Inventory inventory = inventoryDao.findById(id);
    }
    
    public List<InventoryVO> findAllInventory() {
        return inventoryMapper.findAll();
    }
    @Override
    public InventoryVO findInventoryById(Long id) {
        return inventoryMapper.findById(id);
    }

    @Override
    public List<InventoryVO> findInventoryByWarehouseId(Long warehouseId) {
        return inventoryMapper.findByWarehouseId(warehouseId);
    }

    @Override
    public List<InventoryVO> search(String keyword) {
        return inventoryMapper.search(keyword);
    }
}