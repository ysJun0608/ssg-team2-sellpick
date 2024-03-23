package com.ssg.wsmt.smOrders.mapper;

import com.ssg.wsmt.inventory.domain.InventoryVO;
import com.ssg.wsmt.smOrders.domain.ShoppingMallVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ShoppingMallMapper {
    List<ShoppingMallVo> findAll();

    List<InventoryVO> selectAll();
}
