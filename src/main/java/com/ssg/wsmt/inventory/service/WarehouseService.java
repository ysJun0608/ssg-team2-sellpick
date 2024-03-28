package com.ssg.wsmt.inventory.service;

import com.ssg.wsmt.inventory.domain.WarehouseVO;
import com.ssg.wsmt.inventory.dto.PageRequestDTO;
import com.ssg.wsmt.inventory.dto.PageResponseDTO;
import com.ssg.wsmt.inventory.dto.WarehouseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface WarehouseService {
    void createWarehouse(WarehouseDTO warehouseDTO);


    WarehouseVO updateWarehouse();

    WarehouseVO allUpdateWarehouse(WarehouseVO warehouseVO);

    WarehouseVO deliveryUpdateWarehouse(WarehouseVO warehouseVO);

//    List<WarehouseDTO> readAllWarehouse();

    PageResponseDTO<WarehouseDTO> readAllWarehouse(PageRequestDTO pageRequestDTO);
    void readOneWarehouse();
    PageResponseDTO<WarehouseDTO> readAllWarehouseWithSection(PageRequestDTO pageRequestDTO);
}
