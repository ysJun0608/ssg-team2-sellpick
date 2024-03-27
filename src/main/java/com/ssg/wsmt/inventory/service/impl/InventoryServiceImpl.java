package com.ssg.wsmt.inventory.service.impl;


import com.ssg.wsmt.inventory.domain.InventoryVO;
import com.ssg.wsmt.inventory.dto.*;
import com.ssg.wsmt.inventory.mapper.InventoryMapper;
import com.ssg.wsmt.inventory.service.InventoryService;
import com.ssg.wsmt.product.dto.ProductDTO;
import com.ssg.wsmt.product.dto.ProductDTO;
import com.ssg.wsmt.product.dto.ProductsDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class InventoryServiceImpl implements InventoryService {
    private final InventoryMapper inventoryMapper;

//    @Override
//    public List<ProductDTO> findAllProducts() {
//        return inventoryMapper.findAllProducts();
//    }
//
//    public ProductDTO findProductById(Long id) {
//        return inventoryMapper.findProductById(id);
//    }

    public void searchInventory() {
//        System.out.println("찾고자 하는 재고의 id를 입력해주세요 : ");
//        Long id = Long.parseLong(sc.nextLine());
//        Inventory inventory = inventoryDao.findById(id);
    }

    public List<InventoryDTO> findAllInventory() {
        return inventoryMapper.findAll();
    }

    @Override
    public List<InventoryDTO> findAll() {
        return inventoryMapper.findAll();
    }

    @Override
    public List<InventoryDTO> findByWarehouseId(Long warehouseId) {
        return inventoryMapper.findByWarehouseId(warehouseId);
    }

//    @Override
//    public InventoryVO getOne(Long id) {
//        return null;
//    }
//
//    @Override
//    public List<InventoryDTO> searchOrders(Long warehouseId) {
//        return null;
//    }



//    @Override
//    public InvenPageResponseDTO<InventoryDTO> list(InvenPageRequestDTO pageRequestDTO) {
//        int offset = pageRequestDTO.getOffset();
//        int size = pageRequestDTO.getSize();
//
//        List<InventoryDTO> dtoList = inventoryMapper.findAll();
//        int totalCount = inventoryMapper.getTotalCount(pageRequestDTO.getTypes(), pageRequestDTO.getKeyword());
//
//        return InvenPageResponseDTO.<InventoryDTO>withAll()
//                .dtoList(dtoList)
//                .page(pageRequestDTO.getPage()) // 페이지 정보 설정
//                .size(pageRequestDTO.getSize()) // 페이지 크기 설정
//                .total(totalCount) // 전체 항목 수 설정
//                .build();
//    }





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