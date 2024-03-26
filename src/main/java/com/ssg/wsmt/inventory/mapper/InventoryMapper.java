package com.ssg.wsmt.inventory.mapper;

import com.ssg.wsmt.inventory.domain.InventoryVO;
import com.ssg.wsmt.inventory.dto.InventoryDTO;
import com.ssg.wsmt.product.dto.ProductDTO;
import com.ssg.wsmt.product.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface InventoryMapper {
    List<InventoryDTO> findAll();
    List<InventoryDTO> findByWarehouseId(Long warehouseId);
}

