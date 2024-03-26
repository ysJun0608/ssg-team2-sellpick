package com.ssg.wsmt.inventory.mapper;

import com.ssg.wsmt.inventory.domain.InventoryVO;
import com.ssg.wsmt.inventory.dto.InventoryDTO;
import com.ssg.wsmt.product.dto.ProductsDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface InventoryMapper {
    List<ProductDTO> findAllProducts();
    ProductDTO findProductById(Long id);
    List<InventoryVO> findAll();
    InventoryVO findById(Long id);
    List<InventoryVO> findByWarehouseId(@Param("warehouseId") Long warehouseId);
    List<InventoryVO> search(String keyword);

    List<InventoryVO> selectAll();
}
