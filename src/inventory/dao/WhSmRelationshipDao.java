package inventory.dao;

import DBIO.ObjectDBIO;
import inventory.domain.WhSmRelationShip;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class WhSmRelationshipDao extends ObjectDBIO {
    Connection conn = null;
    public void saveWhSmRelationShip(WhSmRelationShip whSmRelationShip) {
        conn = open();
        try {

            String insertToRelationshipsql = "insert into warehouse_shopping_mall_relationship(WAREHOUSE_ID,SM_ID) values (?,?)";
            PreparedStatement pstmt = conn.prepareStatement(insertToRelationshipsql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1, whSmRelationShip.getWarehouseId());
            pstmt.setLong(2, whSmRelationShip.getShoppingMallId());
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
