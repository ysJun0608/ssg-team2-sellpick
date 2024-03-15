package com.ssg.wsmt.inventory.mapper;

import com.ssg.wsmt.inventory.domain.Inventory;
import com.ssg.wsmt.product.domain.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InventoryMapper {
    String getTime();

    List<Inventory> selectAll();
}
