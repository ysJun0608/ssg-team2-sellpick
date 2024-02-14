package delivery.dao;

import DBIO.ObjectDBIO;
import delivery.dto.SelectWaybillOutPut;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WaybillDao extends ObjectDBIO {

    private Connection conn;

    public List<SelectWaybillOutPut> updateWaybill(Long ordersNum) {
        List<SelectWaybillOutPut> outputList = null;
        try {
            //주문테이블의 쇼핑몰id를 통해 쇼핑몰 이름을 운송장 테이블에 조인
            //주문테이블의 상품id를 통해 상품 이름을 운송장 테이블에 조인
            //주문테이블의 고객id를 통해 고객테이블의 이름, 핸드폰번호, 주소를 운송장 테이블에 조인
            //상품테이블의 창고id를 통해 창고테이블의 창고id를 통해 창고 주소를 운송장 테이블에 조인
            //상품테이블의 사업자id를 통해 사업자테이블의 사업자id를 통해 핸드폰을 운송장 테이블에 조인
            //상품테이블의 창고id와 창고구역 테이블의 창고id는 같다.
            //창고구역의 창고id를 통해 창고테이블의 창고id와 같으니 창고 지역을 가져와

            String joinsql = "SELECT w.*, " +
                    "(SELECT s.name FROM shopping_mall s JOIN sm_orders o ON s.id = o.shopping_mall_id WHERE o.id =?) AS shopping_mall_name, " +     //쇼핑몰테이블의 쇼핑몰 id = 주문테이블의 쇼핑몰 id
                    "(SELECT p.name FROM products p JOIN sm_orders o ON p.id = o.products_id WHERE o.id = ?) AS product_name, " + //상품테이블의 상품 id = 주문테이블의 상품 id
                    "(SELECT c.id FROM customer c JOIN sm_orders o ON c.id = o.customer_id WHERE o.id = ?) AS customer_id, " + //고객테이블의 고객 id = 주문테이블의 고객 id
                    "(SELECT c.name FROM customer c JOIN sm_orders o ON c.id = o.customer_id WHERE o.id = ?) AS customer_name, " +
                    "(SELECT c.phone FROM customer c JOIN sm_orders o ON c.id = o.customer_id WHERE o.id = ?) AS customer_phone, " +
                    "(SELECT c.address FROM customer c JOIN sm_orders o ON c.id = o.customer_id WHERE o.id = ?) AS customer_address, " +
                    "(SELECT wh.location FROM warehouse wh JOIN warehouse_section ws ON wh.id = ws.warehouse_id JOIN inventory i ON ws.id = i.wh_section_id JOIN products p ON i.products_id = p.id JOIN sm_orders o ON p.id = o.products_id WHERE o.id = ?) AS warehouse_location, " + //상품테이블의 창고 id = 창고테이블의 창고 id
                    "(SELECT b.phone FROM business_owner b JOIN products p ON b.id = p.owner_id JOIN sm_orders o ON p.id = o.products_id WHERE o.id = ?) AS business_phone " +
                    "FROM waybill w " +
                    "WHERE w.orders_id = ? ";

            //PreparedStatement 얻기 및 값 지정=
            PreparedStatement pstmt = conn.prepareStatement(joinsql);


            // ?의 데이터 값은 모두 ordersNum
            for (int i = 1; i <= 8; i++) {
                pstmt.setLong(i, ordersNum);
            }
            pstmt.setLong(9, ordersNum);

            // sql문 실행
            ResultSet rs = pstmt.executeQuery();

            outputList =  new ArrayList<>();
            //보여줘 !
            while (rs.next()) {
                SelectWaybillOutPut output =
                        new SelectWaybillOutPut(
                                rs.getLong("id"),
                                rs.getLong("orders_id"),
                                rs.getString("product_name"),
                                rs.getString("shopping_mall_name"),
                                rs.getString("warehouse_location"),
                                rs.getString("business_phone"),
                                rs.getLong("customer_id"),
                                rs.getString("customer_name"),
                                rs.getString("customer_phone"),
                                rs.getString("customer_address")
                        );

                outputList.add(output);
            }

            rs.close();
            pstmt.close();
            close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputList;
    }
}