package com.ssg.wsmt.smOrders.mapper;

import com.ssg.wsmt.inventory.domain.Inventory;
import com.ssg.wsmt.smOrders.domain.ShoppingMallVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ShoppingMallMapper {
//    @Select("SELECT * FROM shopping_mall")
    List<ShoppingMallVo> findAll();

    List<Inventory> selectAll();
}
