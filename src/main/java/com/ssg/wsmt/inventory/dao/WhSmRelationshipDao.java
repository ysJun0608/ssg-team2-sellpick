package com.ssg.wsmt.inventory.dao;

import com.ssg.wsmt.DBIO.ObjectDBIO;
import com.ssg.wsmt.inventory.domain.WhSmRelationshipVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class WhSmRelationshipDao extends ObjectDBIO {
    Connection conn = null;
    public void saveWhSmRelationship(WhSmRelationshipVO whSmRelationShipVO) {
        conn = open();
        try {
            String insertToRelationshipsql = "INSERT INTO WAREHOUSE_SHOPPING_MALL_RELATIONSHIP(WAREHOUSE_ID,SM_ID) VALUES (?,?)";
            PreparedStatement pstmt = conn.prepareStatement(insertToRelationshipsql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1, whSmRelationShipVO.getWarehouseId());
            pstmt.setLong(2, whSmRelationShipVO.getShoppingMallId());
            int row = pstmt.executeUpdate();
            if (row == 1) {
                System.out.println("창고생성및 쇼핑몰과의 연동이 완료되었습니다.");
            }
            pstmt.close();
            close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
