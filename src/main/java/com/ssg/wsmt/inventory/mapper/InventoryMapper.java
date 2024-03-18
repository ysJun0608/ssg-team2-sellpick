package com.ssg.wsmt.inventory.mapper;

import com.ssg.wsmt.inventory.domain.InventoryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InventoryMapper {
    String getTime();

    List<InventoryVO> selectAll();
}
