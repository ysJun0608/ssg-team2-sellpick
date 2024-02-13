package inventory.dao;

import DBIO.ObjectDBIO;
import inventory.domain.InsertReleaseType;
import inventory.domain.Warehouse;
import inventory.domain.WarehouseInsertRelease;
import mgtOrders.dao.MgtOrderDao;
import mgtOrders.domain.MgtOrderProductsRelationship;
import product.domain.Products;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class WarehouseInsertReleaseDao extends ObjectDBIO {
    Connection conn = null;
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    // TODO : implement
    ArrayList<WarehouseInsertRelease> warehouseInsertReleases = new ArrayList<>();


    /**
     * 배송이 완료된 관리 주문에 속한 상품들의 정보를 데이터베이스에서 읽어와서 리스트로 반환합니다.
     *
     * @return 배송이 완료된 관리 주문에 속한 상품들의 정보를 담은 ArrayList.
     */
    public ArrayList<MgtOrderProductsRelationship> readProduct() {
        // 읽어온 상품들을 저장할 ArrayList 생성
        ArrayList<MgtOrderProductsRelationship> insertProducts = new ArrayList<>();
        try {
            conn = open();
            // 배송이 완료된 관리 주문에 속한 상품 정보를 조회하는 SQL 쿼리 실행
            String sql = "SELECT * FROM mgt_orders_product_relationship WHERE mgt_orders_id IN (SELECT id FROM mgt_orders WHERE CONFIRM = '배송완료')";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                ResultSet rs = pstmt.executeQuery();

                // 조회한 상품 정보를 MgtOrderProductsRelationship 객체에 매핑하여 리스트에 추가
                while (rs.next()) {
                    MgtOrderProductsRelationship insertProduct = new MgtOrderProductsRelationship();
                    Long id = rs.getLong("ID");
                    Long mgtId = rs.getLong("MGT_ORDERS_ID");
                    Long productsId = rs.getLong("PRODUCTS_ID");
                    Long amount = rs.getLong("QUANTITY");

                    // 객체에 값 설정
                    insertProduct.setProductsId(productsId);
                    insertProduct.setQuantity(amount);
                    insertProduct.setMgtOrdersId(mgtId);
                    insertProduct.setId(id);

                    // 리스트에 추가
                    insertProducts.add(insertProduct);
                }

                // 조회한 상품 정보 출력
                for (MgtOrderProductsRelationship insertProduct : insertProducts) {
                    System.out.println(insertProduct.toString());
                }
            }
            close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 읽어온 상품들의 정보를 담은 ArrayList 반환
        return insertProducts;
    }

    /**
     * @param id
     * @return
     */
    public WarehouseInsertRelease warehouseInsert(Products id) { //
        WarehouseInsertRelease warehouseInsertRelease = new WarehouseInsertRelease();
        try {
            conn = open();
            String sql = new StringBuilder()
                    .append("INSERT INTO warehouse_insert_release ")
                    .append("(AMOUNT, TOTAL_PRICE, CREATED_AT, TYPE, PRODUCTS_ID) ")
                    .append("VALUES (?, ?, now(), ?, ?)")
                    .toString();

            warehouseInsertRelease.setType(InsertReleaseType.INSERT);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(4, warehouseInsertRelease.getType().toString());
            System.out.println("입고된 상품의 번호를 입력해주세요");
            long productid = Long.parseLong(input.readLine());
            pstmt.setLong(5, productid);
            System.out.println("입고된 수량을 입력해주세요");
            long amount = Long.parseLong(input.readLine());
            pstmt.setLong(1, amount);
            System.out.println("입고된 물품의 총 가격을 입력해주세요");
            long totalprice = Long.parseLong(input.readLine());
            pstmt.setLong(2, totalprice);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("입고기록이 생성되었습니다.");
            }
            warehouseInsertRelease.setCreatedAt(LocalDateTime.now());
            warehouseInsertRelease.setAmount(amount);
            warehouseInsertRelease.setTotalPrice(totalprice);
            warehouseInsertRelease.setProductsId(productid);
            pstmt.close();
            close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return warehouseInsertRelease;
    }


    /**
     * @param warehouse
     * @return WarehouseInsertRelease
     * 출고 데이터 생성하는 메소드
     */
    public WarehouseInsertRelease warehouserelease(Warehouse warehouse) { // 창고를 가져오기
        WarehouseInsertRelease warehouseInsertRelease = new WarehouseInsertRelease();
        try {
            open();
            String sql = new StringBuilder()
                    .append("INSERT INTO warehouse_insert_release ")
                    .append("(AMOUNT, TOTAL_PRICE, CREATED_AT, TYPE, PRODUCTS_ID) ")
                    .append("VALUES (?, ?, now(), ?, ?)")
                    .toString();
            // 창고를 파라미터로 받긴하는데 창고를 아직 사용하진 않는다.
            warehouseInsertRelease.setType(InsertReleaseType.RELEASE);
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
            warehouseInsertRelease.setAmount(amount);
            warehouseInsertRelease.setTotalPrice(totalprice);
            warehouseInsertRelease.setProductsId(productid);
            pstmt.close();
            close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return warehouseInsertRelease;
    }

}
