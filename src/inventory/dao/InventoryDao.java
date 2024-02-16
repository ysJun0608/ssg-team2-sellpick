package inventory.dao;

import DBIO.ObjectDBIO;
import inventory.domain.Inventory;
import inventory.dto.InventoryOutput;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryDao extends ObjectDBIO {
    Connection conn = null;

    public List<Inventory> findAll() {
        conn = open();
        List<Inventory> inventoryList = new ArrayList<>();
        try {
            String SQL = "SELECT * FROM INVENTORY";
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Inventory inventory = new Inventory();
                inventory.setId(rs.getLong("ID"));
                inventory.setQuantity(rs.getInt("QUANTITY"));
                inventory.setProductId(rs.getLong("PRODUCTS_ID"));
                inventory.setWhSectionId(rs.getLong("WH_SECTION_ID"));

                inventoryList.add(inventory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventoryList;
    }

    public Inventory findById(Long id) {
        conn = open();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

                Inventory inventory = new Inventory();

        try {
            String SQL = "SELECT * FROM INVENTORY WHERE ID = ?";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setLong(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                inventory.setId(rs.getLong("ID"));
                inventory.setQuantity(rs.getInt("QUANTITY"));
                inventory.setProductId(rs.getLong("PRODUCTS_ID"));
                inventory.setWhSectionId(rs.getLong("WH_SECTION_ID"));
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn);
        }
        return inventory;
    }

    public List<InventoryOutput> findByWarehouseId(Long id) {
        conn = open();
        List<InventoryOutput> inventoryList = new ArrayList<>();
        try {
            // 창고별 재고 조회, InventoryOutput으로 변경
            String SQL = "SELECT I.ID, I.QUANTITY, I.WH_SECTION_ID, I.PRODUCTS_ID, " +
                    "W.ID AS WAREHOUSE_ID, " +
                    "W.LOCATION, " +
                    "P.NAME AS PRODUCT_NAME, " +
                    "B.NAME AS BRAND_NAME, " +
                    "P.PRICE AS PRODUCT_PRICE " +
                    "FROM INVENTORY I " +
                    "JOIN WAREHOUSE_SECTION WS " +
                    "   ON I.WH_SECTION_ID = WS.ID " +
                    "JOIN WAREHOUSE W " +
                    "   ON WS.WAREHOUSE_ID = W.ID " +
                    "JOIN PRODUCTS P " +
                    "   ON I.PRODUCTS_ID = P.ID " +
                    "JOIN BRAND B " +
                    "   ON P.BRAND_ID = B.ID " +
                    "WHERE W.ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                InventoryOutput inventoryOutput = InventoryOutput.builder()
                        .id(rs.getLong("ID"))
                        .quantity(rs.getLong("QUANTITY"))
                        .warehouseSectionId(rs.getLong("WH_SECTION_ID"))
                        .productId(rs.getLong("PRODUCTS_ID"))
                        .warehouseId(rs.getLong("WAREHOUSE_ID"))
                        .location(rs.getString("LOCATION"))
                        .productName(rs.getString("PRODUCT_NAME"))
                        .brandName(rs.getString("BRAND_NAME"))
                        .productPrice(rs.getInt("PRODUCT_PRICE"))
                        .build();
                inventoryList.add(inventoryOutput);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventoryList;
    }
}
