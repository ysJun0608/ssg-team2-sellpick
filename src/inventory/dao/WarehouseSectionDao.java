package inventory.dao;

import DBIO.ObjectDBIO;
import inventory.domain.Warehouse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WarehouseSectionDao extends ObjectDBIO {
    Connection conn = null;

    public void saveWarehouseSection(Warehouse warehouse) {
        try {
            conn = open();

            // SQL 쿼리문 정의
            String sql = "INSERT INTO WAREHOUSE_SECTION (WAREHOUSE_ID, NAME) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            Long warehouseId = warehouse.getId();

            // 냉동식품 섹션 추가
            pstmt.setLong(1, warehouseId);
            pstmt.setString(2, "냉동식품");
            pstmt.executeUpdate();

            // 냉장식품 섹션 추가
            pstmt.setLong(1, warehouseId);
            pstmt.setString(2, "냉장식품");
            pstmt.executeUpdate();

            // 가공식품 섹션 추가
            pstmt.setLong(1, warehouseId);
            pstmt.setString(2, "가공식품");
            pstmt.executeUpdate();

            // 건조식품 섹션 추가
            pstmt.setLong(1, warehouseId);
            pstmt.setString(2, "건조식품");
            pstmt.executeUpdate();

            pstmt.close();
            close(conn);

        } catch (SQLException e) {
            e.printStackTrace(); // 실제 상황에 따라 로깅 등으로 변경
        }
    }
}
