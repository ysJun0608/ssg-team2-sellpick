package delivery.dao;

import DBIO.ObjectDBIO;
import delivery.dto.SelectListWaybillOutput;
import delivery.dto.SelectWaybillOutPut;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WaybillDao extends ObjectDBIO {

    private Connection conn;

    public List<SelectWaybillOutPut> findByOrderId(Long ordersNum) {
        List<SelectWaybillOutPut> outputList = null;
        conn = open();
        try {
            //주문테이블의 쇼핑몰id를 통해 쇼핑몰 이름을 운송장 테이블에 조인
            //주문테이블의 상품id를 통해 상품 이름을 운송장 테이블에 조인
            //주문테이블의 고객id를 통해 고객테이블의 이름, 핸드폰번호, 주소를 운송장 테이블에 조인
            //상품테이블의 창고id를 통해 창고테이블의 창고id를 통해 창고 주소를 운송장 테이블에 조인
            //상품테이블의 사업자id를 통해 사업자테이블의 사업자id를 통해 핸드폰을 운송장 테이블에 조인
            //상품테이블의 창고id와 창고구역 테이블의 창고id는 같다.
            //창고구역의 창고id를 통해 창고테이블의 창고id와 같으니 창고 지역을 가져와

            String joinsql = "SELECT W.*, " +
                    "(SELECT S.NAME FROM SHOPPING_MALL S JOIN SM_ORDERS O ON S.ID = O.SHOPPING_MALL_ID WHERE O.ID =?) AS SHOPPING_MALL_NAME, " +     //쇼핑몰테이블의 쇼핑몰 ID = 주문테이블의 쇼핑몰 ID
                    "(SELECT P.NAME FROM PRODUCTS P JOIN SM_ORDERS O ON P.ID = O.PRODUCTS_ID WHERE O.ID = ?) AS PRODUCT_NAME, " + //상품테이블의 상품 ID = 주문테이블의 상품 ID
                    "(SELECT C.ID FROM CUSTOMER C JOIN SM_ORDERS O ON C.ID = O.CUSTOMER_ID WHERE O.ID = ?) AS CUSTOMER_ID, " + //고객테이블의 고객 ID = 주문테이블의 고객 ID
                    "(SELECT C.NAME FROM CUSTOMER C JOIN SM_ORDERS O ON C.ID = O.CUSTOMER_ID WHERE O.ID = ?) AS CUSTOMER_NAME, " +
                    "(SELECT C.PHONE FROM CUSTOMER C JOIN SM_ORDERS O ON C.ID = O.CUSTOMER_ID WHERE O.ID = ?) AS CUSTOMER_PHONE, " +
                    "(SELECT C.ADDRESS FROM CUSTOMER C JOIN SM_ORDERS O ON C.ID = O.CUSTOMER_ID WHERE O.ID = ?) AS CUSTOMER_ADDRESS, " +
                    "(SELECT WH.LOCATION FROM WAREHOUSE WH JOIN WAREHOUSE_SECTION WS ON WH.ID = WS.WAREHOUSE_ID JOIN INVENTORY I ON WS.ID = I.WH_SECTION_ID JOIN PRODUCTS P ON I.PRODUCTS_ID = P.ID JOIN SM_ORDERS O ON P.ID = O.PRODUCTS_ID WHERE O.ID = ?) AS WAREHOUSE_LOCATION, " + //상품테이블의 창고 ID = 창고테이블의 창고 ID
                    "(SELECT B.PHONE FROM BUSINESS_OWNER B JOIN PRODUCTS P ON B.ID = P.OWNER_ID JOIN SM_ORDERS O ON P.ID = O.PRODUCTS_ID WHERE O.ID = ?) AS BUSINESS_PHONE " +
                    "FROM WAYBILL W " +
                    "WHERE W.ORDERS_ID = ? ";

            //PreparedStatement 얻기 및 값 지정=
            PreparedStatement pstmt = conn.prepareStatement(joinsql);


            // ?의 데이터 값은 모두 ordersNum
            for (int i = 1; i <= 8; i++) {
                pstmt.setLong(i, ordersNum);
            }
            pstmt.setLong(9, ordersNum);

            // sql문 실행
            ResultSet rs = pstmt.executeQuery();

            outputList = new ArrayList<>();
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

    public List<SelectListWaybillOutput> readWaybill() {
        conn = open();
        List<SelectListWaybillOutput> outputList = null;
        try {

            //고객이름 주소 상품이름 창고지역주소

            String listsql = "SELECT\n" +
                    "    WAYBILL.ID AS WAYBILL_ID,\n" +
                    "    SM_ORDERS.ID AS SM_ORDERS_ID,\n" +
                    "    CUSTOMER.NAME AS CUSTOMER_NAME,\n" +
                    "    CUSTOMER.ADDRESS AS CUSTOMER_ADDRESS,\n" +
                    "    PRODUCTS.NAME AS PRODUCTS_NAME,\n" +
                    "    WAREHOUSE.LOCATION AS WAREHOUSE_LOCATION\n" +
                    "FROM\n" +
                    "    WAYBILL\n" +
                    "JOIN\n" +
                    "    SM_ORDERS ON WAYBILL.ORDERS_ID = SM_ORDERS.ID\n" +
                    "JOIN\n" +
                    "    CUSTOMER ON SM_ORDERS.CUSTOMER_ID = CUSTOMER.ID\n" +
                    "JOIN\n" +
                    "    PRODUCTS ON SM_ORDERS.PRODUCTS_ID = PRODUCTS.ID\n" +
                    "JOIN\n" +
                    "    INVENTORY ON PRODUCTS.ID = INVENTORY.PRODUCTS_ID\n" +
                    "JOIN\n" +
                    "    WAREHOUSE_SECTION ON INVENTORY.WH_SECTION_ID = WAREHOUSE_SECTION.ID\n" +
                    "JOIN\n" +
                    "    WAREHOUSE ON WAREHOUSE_SECTION.WAREHOUSE_ID = WAREHOUSE.ID;";

            //PreparedStatement 얻기 및 값 지정=
            PreparedStatement pstmt = conn.prepareStatement(listsql);

            // sql문 실행
            ResultSet rs = pstmt.executeQuery();

            outputList = new ArrayList<>();
            //보여줘 !
            while (rs.next()) {
                SelectListWaybillOutput output =
                        new SelectListWaybillOutput(
                                rs.getLong("WAYBILL_ID"),
                                rs.getLong("SM_ORDERS_ID"),
                                rs.getString("CUSTOMER_NAME"),
                                rs.getString("CUSTOMER_ADDRESS"),
                                rs.getString("PRODUCTS_NAME"),
                                rs.getString("WAREHOUSE_LOCATION")
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
