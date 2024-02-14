package inventory.dao;

import DBIO.ObjectDBIO;
import inventory.domain.Warehouse;
import inventory.domain.WarehouseSection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WarehouseSectionDao extends ObjectDBIO {
    Connection conn = null;

    public boolean saveWarehouseSection(WarehouseSection section) {
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
}
