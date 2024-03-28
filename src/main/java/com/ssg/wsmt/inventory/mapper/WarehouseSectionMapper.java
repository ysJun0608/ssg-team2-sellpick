package com.ssg.wsmt.inventory.mapper;

import com.ssg.wsmt.inventory.domain.WarehouseSectionVO;
import com.ssg.wsmt.inventory.enums.WhSectionType;
import org.apache.ibatis.annotations.Mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
@Mapper
public interface WarehouseSectionMapper {
    Long saveWarehouseSection(WarehouseSectionVO section);
    ArrayList selectWarehouseSection(Long chooseScId);
}
