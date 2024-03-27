package com.ssg.wsmt.inventory.mapper;

import com.ssg.wsmt.inventory.domain.InventoryVO;
import com.ssg.wsmt.inventory.dto.InventoryDTO;
import com.ssg.wsmt.product.dto.ProductDTO;
import com.ssg.wsmt.product.dto.ProductDTO;
import com.ssg.wsmt.smOrders.enums.SellerSendStatus;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface InventoryMapper {
    List<InventoryDTO> findAll();
    List<InventoryDTO> findByWarehouseId(Long warehouseId);
    List<InventoryDTO> findOrdersByCriteria( Long orderId , Long customerId, SellerSendStatus sendStatus);
    List<InventoryDTO> searchAll(@Param("types") String[] types, @Param("keyword") String keyword, @Param("offset") int offset, @Param("size") int size);
    int getTotalCount(@Param("types") String[] types, @Param("keyword") String keyword);
}

