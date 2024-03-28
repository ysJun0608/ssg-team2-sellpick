package com.ssg.wsmt.inventory.dao;

import com.ssg.wsmt.DBIO.ObjectDBIO;
import com.ssg.wsmt.inventory.domain.WarehouseSectionVO;
import com.ssg.wsmt.inventory.enums.WhSectionType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WarehouseSectionDao extends ObjectDBIO {
    Connection conn = null;

    public boolean saveWarehouseSection(WarehouseSectionVO section) {
        boolean result = false;
        try {
            conn = open();
            // SQL 쿼리문 정의
            String sql = "INSERT INTO WAREHOUSE_SECTION (WAREHOUSE_ID, NAME, TYPE) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 냉동식품 섹션 추가
            pstmt.setLong(1, section.getWarehouseId());
            pstmt.setString(2, section.getName());
            pstmt.setString(3, section.getType().name());
           int row =  pstmt.executeUpdate();
            if (row>0) {
                result = true;
            }
            pstmt.close();
            close(conn);

        } catch (SQLException e) {
            e.printStackTrace(); // 실제 상황에 따라 로깅 등으로 변경
        }
        return result;
    }


    public ArrayList selectWarehouseSection(Long chooseScId) {
        ArrayList<WarehouseSectionVO> warehouseSectionVOS = new ArrayList<>();

        try { conn = open();
            String sql = "SELECT * FROM WAREHOUSE_SECTION WHERE WAREHOUSE_ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, String.valueOf(chooseScId));
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                WarehouseSectionVO warehouseSectionVO = new WarehouseSectionVO();
                warehouseSectionVO.setName(rs.getString("NAME"));
                warehouseSectionVO.setType(WhSectionType.valueOf(rs.getString("TYPE")));
                warehouseSectionVOS.add(warehouseSectionVO);
            }
            close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return warehouseSectionVOS;
    }
}
