package com.ssg.wsmt.inventory.service.impl;


import com.ssg.wsmt.inventory.dto.InventoryDTO;
import com.ssg.wsmt.inventory.dto.PageRequestDTO;
import com.ssg.wsmt.inventory.dto.PageResponseDTO;
import com.ssg.wsmt.inventory.mapper.InventoryMapper;
import com.ssg.wsmt.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Log4j2
public class InventoryServiceImpl implements InventoryService {
    private final InventoryMapper inventoryMapper;

    @Override
    public List<InventoryDTO> findAll() {
        return inventoryMapper.findAll();
    }

    @Override
    public List<InventoryDTO> findByWarehouseId(Long warehouseId) {
        return inventoryMapper.findByWarehouseId(warehouseId);
    }

//    @Override
//    public PageResponseDTO<InventoryDTO> findWithPagination(PageRequestDTO pageRequestDTO) {
//        return null;
//    }
//
//    @Override
//    public PageResponseDTO<InventoryDTO> findByWarehouseIdWithPagination(Long warehouseId, PageRequestDTO pageRequestDTO) {
//        return null;
//    }
}