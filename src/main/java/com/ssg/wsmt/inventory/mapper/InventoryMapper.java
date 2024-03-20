package com.ssg.wsmt.inventory.mapper;

import com.ssg.wsmt.inventory.domain.Inventory;
import com.ssg.wsmt.inventory.dto.InventoryOutput;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InventoryMapper {
    List<Inventory> findAll();
    Inventory findById(Long id);
    List<InventoryOutput> findByWarehouseId(Long id);
}