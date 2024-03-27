package com.ssg.wsmt.inventory.service.impl;


import com.ssg.wsmt.inventory.domain.InventoryVO;
import com.ssg.wsmt.inventory.dto.InventoryDTO;
import com.ssg.wsmt.inventory.dto.PageRequestDTO;
import com.ssg.wsmt.inventory.dto.PageResponseDTO;
import com.ssg.wsmt.inventory.mapper.InventoryMapper;
import com.ssg.wsmt.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public InventoryVO getOne(Long id) {
        return null;
    }

    @Override
    public List<InventoryDTO> searchOrders(Long warehouseId) {
        return null;
    }



    @Override
    public PageResponseDTO<InventoryDTO> list(PageRequestDTO pageRequestDTO) {
        int offset = pageRequestDTO.getOffset();
        int size = pageRequestDTO.getSize();

        List<InventoryDTO> dtoList = inventoryMapper.findAll();
        int totalCount = inventoryMapper.getTotalCount(pageRequestDTO.getTypes(), pageRequestDTO.getKeyword());

        return PageResponseDTO.<InventoryDTO>withAll()
                .dtoList(dtoList)
                .page(pageRequestDTO.getPage()) // 페이지 정보 설정
                .size(pageRequestDTO.getSize()) // 페이지 크기 설정
                .total(totalCount) // 전체 항목 수 설정
                .build();
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