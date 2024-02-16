package inventory.dao;

import DBIO.ObjectDBIO;
import inventory.domain.Warehouse;
import inventory.enums.WhInOutType;
import mgtOrders.domain.WarehouseInsertRelease;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class WarehouseInsertReleaseDao extends ObjectDBIO {
    Connection conn = null;
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    // TODO : implement
    ArrayList<inventory.domain.WarehouseInsertRelease> warehouseInsertReleases = new ArrayList<>();


    /**
     * 배송이 완료된 관리 주문에 속한 상품들의 정보를 데이터베이스에서 읽어와서 리스트로 반환합니다.
     *
     * @return 배송이 완료된 관리 주문에 속한 상품들의 정보를 담은 ArrayList.
     * 창고로 지금 현재 들어온 배송완료 처리가 된 상품들을 조회하는 메소드
     */
    public ArrayList<WarehouseInsertRelease> findAllInsertProducts() {
        // 읽어온 상품들을 저장할 ArrayList 생성
        ArrayList<WarehouseInsertRelease> insertProducts = new ArrayList<>();
        try {
            conn = open();
            // 배송이 완료된 관리 주문에 속한 상품 정보를 조회하는 SQL 쿼리 실행
            String sql = "SELECT * FROM MGT_ORDERS_PRODUCTS_RELATIONSHIP WHERE MGT_ORDERS_ID IN (SELECT ID FROM MGT_ORDERS WHERE STATUS = 'DONE')";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                ResultSet rs = pstmt.executeQuery();
                // 조회한 상품 정보를 MgtOrderProductsRelationship 객체에 매핑하여 리스트에 추가
                while (rs.next()) {
                    WarehouseInsertRelease insertProduct = new WarehouseInsertRelease();
                    Long id = rs.getLong("ID");
                    Long mgtId = rs.getLong("MGT_ORDERS_ID");
                    Long productsId = rs.getLong("PRODUCTS_ID");
                    int amount = rs.getInt("QUANTITY");
                    // 객체에 값 설정
                    insertProduct.setProductsId(productsId);
                    insertProduct.setQuantity(amount);
                    insertProduct.setMgtOrdersId(mgtId);
                    insertProduct.setId(id);
                    // 리스트에 추가
                    insertProducts.add(insertProduct);
                }
                // 조회한 상품 정보 출력
             /*   for (MgtOrderProductsRelationship insertProduct : insertProducts) {
                    System.out.println(insertProduct.toString());
                }*/
            }
            close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 읽어온 상품들의 정보를 담은 ArrayList 반환
        return insertProducts;
    }

    /**
     * 발송되어 오는 물품(DONE)인상태를 창고기준에서 체크하고 INSERT_REQUEST로 변경하는 메소드
     *
     * @param allInsertProducts 주문된 상품들의 목록
     * @return WarehouseInsertRelease 객체
     */
    public inventory.domain.WarehouseInsertRelease updateInsertStatus(ArrayList<WarehouseInsertRelease> allInsertProducts) {
        inventory.domain.WarehouseInsertRelease warehouseInsertRelease = new inventory.domain.WarehouseInsertRelease();
        try {
            conn = open();
            // SQL 쿼리문을 미리 준비합니다.
            String sql = new StringBuilder("INSERT INTO WAREHOUSE_INSERT_RELEASE ")
                    .append("(QUANTITY, TYPE, TOTAL_PRICE, INVENTORY_ID, FK_ID) ")
                    .append("VALUES (?, ?, (SELECT COST FROM PRODUCTS WHERE ID = ?) * ?, ")
                    .append("(SELECT WAREHOUSE_ID FROM MGT_ORDERS WHERE ID = ?), ")
                    .append("?)")
                    .toString();
            // PreparedStatement 객체를 생성합니다.
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // 주문된 상품들을 처리하기 위해 Batch 처리를 수행합니다.
            for (WarehouseInsertRelease dto : allInsertProducts) {
                pstmt.setLong(1, dto.getQuantity());
                pstmt.setString(2, WhInOutType.INSERT_WAIT.name());
                pstmt.setLong(3, dto.getProductsId());
                pstmt.setLong(4, dto.getQuantity());
                pstmt.setLong(5, dto.getMgtOrdersId());
                pstmt.setLong(6, dto.getMgtOrdersId());
                // Batch에 추가하고 매번 매개변수를 초기화합니다.
                pstmt.addBatch();
                pstmt.clearParameters();
            }
            // Batch 실행으로 여러 개의 쿼리를 일괄 처리합니다.
            pstmt.executeBatch();
            // 데이터베이스 연결을 닫습니다.
            close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return warehouseInsertRelease;
    }







    /**
     * INSERT_CONFIRM 처리된 재고를 재고테이블로 넣는 메소드
     */

   /* public ArrayList createInventorys(ArrayList<MgtOrderProductsRelationship> allInsertProducts) {
        try {
            conn = open();
            String sql = new StringBuilder("INSERT INTO INVENTORY (?,?) ")
                    .append("VALUES");

            close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allInsertProducts;
    }*/


    /**
     * @param warehouse
     * @return WarehouseInsertRelease
     * 출고 데이터 생성하는 메소드
     */
    public inventory.domain.WarehouseInsertRelease warehouserelease(Warehouse warehouse) { // 창고를 가져오기
        inventory.domain.WarehouseInsertRelease warehouseInsertRelease = new inventory.domain.WarehouseInsertRelease();
        try {
            open();
            String sql = new StringBuilder()
                    .append("INSERT INTO warehouse_insert_release ")
                    .append("(AMOUNT, TOTAL_PRICE, CREATED_AT, TYPE, PRODUCTS_ID) ")
                    .append("VALUES (?, ?, now(), ?, ?)")
                    .toString();
            // 창고를 파라미터로 받긴하는데 창고를 아직 사용하진 않는다.

            warehouseInsertRelease.setType(WhInOutType.RELEASE_CONFIRM);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(4, warehouseInsertRelease.getType().toString());
            System.out.println("출고된 상품의 번호를 입력해주세요");
            long productid = Long.parseLong(input.readLine());
            pstmt.setLong(5, productid);
            System.out.println("출고된 수량을 입력해주세요");
            long amount = Long.parseLong(input.readLine());
            pstmt.setLong(1, amount);
            System.out.println("출고된 물품의 총 가격을 입력해주세요");
            long totalprice = Long.parseLong(input.readLine());
            pstmt.setLong(2, totalprice);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("출고기록이 생성되었습니다.");
            }
            warehouseInsertRelease.setCreatedAt(LocalDateTime.now());
            warehouseInsertRelease.setQuantity(amount);
            warehouseInsertRelease.setTotalPrice(totalprice);
            warehouseInsertRelease.setInventoryId(productid);
            pstmt.close();
            close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return warehouseInsertRelease;
    }

    // RELEASE
    public void updateWhInOutStatus(Long id, WhInOutType whInOutType) {
        conn = open();
        PreparedStatement pstmt = null;

        try {
            String SQL = "UPDATE WAREHOUSE_INSERTY_RELEASE SET STATUS = ? WHERE ID = ?";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, whInOutType.name());
            pstmt.setLong(2, id);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<WarehouseInsertRelease> findAllWhInOutList(WhInOutType whInOutType) {
        // 읽어온 상품들을 저장할 ArrayList 생성
        ArrayList<WarehouseInsertRelease> WhInOutList = new ArrayList<>();
        try {
            conn = open();
            // 배송이 완료된 관리 주문에 속한 상품 정보를 조회하는 SQL 쿼리 실행
            String sql = "SELECT * FROM WAREHOUSE_INSERT_RELEASE WHERE TYPE = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, whInOutType.name());
                ResultSet rs = pstmt.executeQuery();
                // 조회한 상품 정보를 MgtOrderProductsRelationship 객체에 매핑하여 리스트에 추가
                while (rs.next()) {
                    WarehouseInsertRelease WhInOut = new WarehouseInsertRelease();
                    Long id = rs.getLong("ID");
                    int quantity = rs.getInt("QUANTITY");
                    Long smtOrdersId = rs.getLong("FK_ID");
                    Long productsId = rs.getLong("PRODUCTS_ID");
                    int totalPrice = rs.getInt("TOTAL_PRICE");
                    WhInOutType type = WhInOutType.valueOf(rs.getString("TYPE"));
                    // 객체에 값 설정
                    WhInOut.setId(id);
                    WhInOut.setQuantity(quantity);
                    WhInOut.setMgtOrdersId(smtOrdersId);
                    WhInOut.setProductsId(productsId);
                    WhInOut.setTotalPrice(totalPrice);
                    WhInOut.setWhInOutType(type);
                    WhInOut.setCreatedAt(rs.getTimestamp("CREATED_AT").toLocalDateTime());
                    // 리스트에 추가
                    WhInOutList.add(WhInOut);
                }
            }
            close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 읽어온 상품들의 정보를 담은 ArrayList 반환
        return WhInOutList;
    }
}
