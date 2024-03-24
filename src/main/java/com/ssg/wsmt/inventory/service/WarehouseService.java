package com.ssg.wsmt.inventory.service;

import com.ssg.wsmt.inventory.domain.WarehouseVO;
import com.ssg.wsmt.inventory.dto.WarehouseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WarehouseService {
    void createWarehouse(WarehouseDTO warehouseDTO);


    WarehouseVO updateWarehouse();

    WarehouseVO allUpdateWarehouse(WarehouseVO warehouseVO);

    WarehouseVO deliveryUpdateWarehouse(WarehouseVO warehouseVO);

    List<WarehouseDTO> readAllWarehouse();

    void readOneWarehouse();
}
