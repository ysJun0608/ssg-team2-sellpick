package smOrders.dao;

import DBIO.ObjectDBIO;
import smOrders.domain.SellerSendStatus;
import smOrders.domain.smOrders;
import smOrders.dto.SmOrdersOutput;

import java.sql.*;
import java.util.*;

public class SMOrdersDao extends ObjectDBIO {

    static Scanner sc = new Scanner(System.in);

    //주문 전체 조회
    public List<SmOrdersOutput> smOrdersReadAllCanCel() {
        Connection conn = null;
        List<SmOrdersOutput> outputList = new ArrayList<>();

        try {
            conn = open(); // ObjectDBIO open메소드 호출

            String sql = "SELECT \n" +
                    "    SO.ID AS order_id,\n" +
                    "    SO.QUANTITY,\n" +
                    "    SO.PAYMENT_AMOUNT,\n" +
                    "    SO.CREATED_AT,\n" +
                    "    SO.EXPECTED_AT,\n" +
                    "    SO.SELLER_SEND_STATUS,\n" +
                    "    SO.CUSTOMER_ID,\n" +
                    "    SO.SHOPPING_MALL_ID,\n" +
                    "    SO.PRODUCTS_ID,\n" +
                    "    SGM.NAME AS SHOPPING_NAME,\n" +
                    "    PDS.NAME AS PRODUCTS_NAME,\n" +
                    "    PDS.PRICE AS PRODUCTS_PRICE,\n" +
                    "    PDS.OWNER_ID AS PRODUCTS_OWNER_ID,\n" +
                    "    PDS.BRAND_ID AS PRODUCTS_BRAND_ID,\n" +
                    "    BSOR.NAME AS OWNER_NAME,\n" +
                    "    BSOR.PHONE AS OWNER_PHONE,\n" +
                    "    CTR.NAME AS CUSTOMER_NAME,\n" +
                    "    CTR.PHONE AS CUSTOMER_PHONE,\n" +
                    "    WHE.LOCATION AS WAREHOUSE_LOCATION\n" +
                    "FROM SM_ORDERS SO\n" +
                    "JOIN SHOPPING_MALL SGM ON SO.SHOPPING_MALL_ID = SGM.ID\n" +
                    "JOIN CUSTOMER CTR ON SO.CUSTOMER_ID = CTR.ID\n" +
                    "JOIN PRODUCTS PDS ON SO.PRODUCTS_ID = PDS.ID\n" +
                    "JOIN BUSINESS_OWNER BSOR ON PDS.OWNER_ID = BSOR.ID\n" +
                    "JOIN WAREHOUSE_SHOPPING_MALL_RELATIONSHIP WSMR ON WSMR.ID = SO.SHOPPING_MALL_ID\n" +
                    "JOIN WAREHOUSE WHE ON WSMR.WAREHOUSE_ID = WHE.ID\n" +
                    "WHERE SO.SHOPPING_MALL_ID IN (SELECT ID FROM SHOPPING_MALL)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                SmOrdersOutput output = new SmOrdersOutput(
                        rs.getLong("order_id"),
                        rs.getInt("quantity"),
                        rs.getInt("payment_amount"),
                        rs.getDate("created_at"),
                        rs.getDate("expected_at"),
                        rs.getString("seller_send_status"),
                        rs.getLong("customer_id"),
                        rs.getLong("shopping_mall_id"),
                        rs.getString("shopping_name"),
                        rs.getLong("products_id"),
                        rs.getString("products_name"),
                        rs.getInt("products_price"),
                        rs.getString("products_owner_id"),
                        rs.getLong("products_brand_id"),
                        rs.getString("owner_name"),
                        rs.getString("owner_phone"),
                        rs.getString("customer_name"),
                        rs.getString("customer_phone"),
                        rs.getString("warehouse_location")
                );

                outputList.add(output);
            }

            close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return outputList;
    }

    //주문 배송준비중 조회
    public List<SmOrdersOutput> readAllPreparedOrders() {
        Connection conn = null;
        List<SmOrdersOutput> outputList = new ArrayList<>();

        try {
            conn = open(); // ObjectDBIO open메소드 호출

            String sql = "SELECT \n" +
                    "    SO.ID AS order_id,\n" +
                    "    SO.QUANTITY,\n" +
                    "    SO.PAYMENT_AMOUNT,\n" +
                    "    SO.CREATED_AT,\n" +
                    "    SO.EXPECTED_AT,\n" +
                    "    SO.SELLER_SEND_STATUS,\n" +
                    "    SO.CUSTOMER_ID,\n" +
                    "    SO.SHOPPING_MALL_ID,\n" +
                    "    SO.PRODUCTS_ID,\n" +
                    "    SGM.NAME AS SHOPPING_NAME,\n" +
                    "    PDS.NAME AS PRODUCTS_NAME,\n" +
                    "    PDS.PRICE AS PRODUCTS_PRICE,\n" +
                    "    PDS.OWNER_ID AS PRODUCTS_OWNER_ID,\n" +
                    "    PDS.BRAND_ID AS PRODUCTS_BRAND_ID,\n" +
                    "    BSOR.NAME AS OWNER_NAME,\n" +
                    "    BSOR.PHONE AS OWNER_PHONE,\n" +
                    "    CTR.NAME AS CUSTOMER_NAME,\n" +
                    "    CTR.PHONE AS CUSTOMER_PHONE,\n" +
                    "    WHE.LOCATION AS WAREHOUSE_LOCATION\n" +
                    "FROM SM_ORDERS SO\n" +
                    "JOIN SHOPPING_MALL SGM ON SO.SHOPPING_MALL_ID = SGM.ID\n" +
                    "JOIN CUSTOMER CTR ON SO.CUSTOMER_ID = CTR.ID\n" +
                    "JOIN PRODUCTS PDS ON SO.PRODUCTS_ID = PDS.ID\n" +
                    "JOIN BUSINESS_OWNER BSOR ON PDS.OWNER_ID = BSOR.ID\n" +
                    "JOIN WAREHOUSE_SHOPPING_MALL_RELATIONSHIP WSMR ON WSMR.ID = SO.SHOPPING_MALL_ID\n" +
                    "JOIN WAREHOUSE WHE ON WSMR.WAREHOUSE_ID = WHE.ID\n" +
                    "WHERE SO.SELLER_SEND_STATUS = 'PREPARING' \n" +
                    "AND SO.SHOPPING_MALL_ID IN (SELECT ID FROM SHOPPING_MALL) ";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                SmOrdersOutput output = new SmOrdersOutput(
                        rs.getLong("order_id"),
                        rs.getInt("quantity"),
                        rs.getInt("payment_amount"),
                        rs.getDate("created_at"),
                        rs.getDate("expected_at"),
                        rs.getString("seller_send_status"),
                        rs.getLong("customer_id"),
                        rs.getLong("shopping_mall_id"),
                        rs.getString("shopping_name"),
                        rs.getLong("products_id"),
                        rs.getString("products_name"),
                        rs.getInt("products_price"),
                        rs.getString("products_owner_id"),
                        rs.getLong("products_brand_id"),
                        rs.getString("owner_name"),
                        rs.getString("owner_phone"),
                        rs.getString("customer_name"),
                        rs.getString("customer_phone"),
                        rs.getString("warehouse_location")
                );

                outputList.add(output);
            }

            close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return outputList;
    }

    //배송취소건 조회
    public List<SmOrdersOutput> readAllCanceledOrders() {
        Connection conn = null;
        List<SmOrdersOutput> outputList = new ArrayList<>();

        try {
            conn = open(); // ObjectDBIO open메소드 호출

            String sql = "SELECT \n" +
                    "    SO.ID AS order_id,\n" +
                    "    SO.QUANTITY,\n" +
                    "    SO.PAYMENT_AMOUNT,\n" +
                    "    SO.CREATED_AT,\n" +
                    "    SO.EXPECTED_AT,\n" +
                    "    SO.SELLER_SEND_STATUS,\n" +
                    "    SO.CUSTOMER_ID,\n" +
                    "    SO.SHOPPING_MALL_ID,\n" +
                    "    SO.PRODUCTS_ID,\n" +
                    "    SGM.NAME AS SHOPPING_NAME,\n" +
                    "    PDS.NAME AS PRODUCTS_NAME,\n" +
                    "    PDS.PRICE AS PRODUCTS_PRICE,\n" +
                    "    PDS.OWNER_ID AS PRODUCTS_OWNER_ID,\n" +
                    "    PDS.BRAND_ID AS PRODUCTS_BRAND_ID,\n" +
                    "    BSOR.NAME AS OWNER_NAME,\n" +
                    "    BSOR.PHONE AS OWNER_PHONE,\n" +
                    "    CTR.NAME AS CUSTOMER_NAME,\n" +
                    "    CTR.PHONE AS CUSTOMER_PHONE,\n" +
                    "    WHE.LOCATION AS WAREHOUSE_LOCATION\n" +
                    "FROM SM_ORDERS SO\n" +
                    "JOIN SHOPPING_MALL SGM ON SO.SHOPPING_MALL_ID = SGM.ID\n" +
                    "JOIN CUSTOMER CTR ON SO.CUSTOMER_ID = CTR.ID\n" +
                    "JOIN PRODUCTS PDS ON SO.PRODUCTS_ID = PDS.ID\n" +
                    "JOIN BUSINESS_OWNER BSOR ON PDS.OWNER_ID = BSOR.ID\n" +
                    "JOIN WAREHOUSE_SHOPPING_MALL_RELATIONSHIP WSMR ON WSMR.ID = SO.SHOPPING_MALL_ID\n" +
                    "JOIN WAREHOUSE WHE ON WSMR.WAREHOUSE_ID = WHE.ID\n" +
                    "WHERE SO.SELLER_SEND_STATUS = 'CANCEL' \n" +
                    "AND SO.SHOPPING_MALL_ID IN (SELECT ID FROM SHOPPING_MALL) ";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                SmOrdersOutput output = new SmOrdersOutput(
                        rs.getLong("order_id"),
                        rs.getInt("quantity"),
                        rs.getInt("payment_amount"),
                        rs.getDate("created_at"),
                        rs.getDate("expected_at"),
                        rs.getString("seller_send_status"),
                        rs.getLong("customer_id"),
                        rs.getLong("shopping_mall_id"),
                        rs.getString("shopping_name"),
                        rs.getLong("products_id"),
                        rs.getString("products_name"),
                        rs.getInt("products_price"),
                        rs.getString("products_owner_id"),
                        rs.getLong("products_brand_id"),
                        rs.getString("owner_name"),
                        rs.getString("owner_phone"),
                        rs.getString("customer_name"),
                        rs.getString("customer_phone"),
                        rs.getString("warehouse_location")
                );

                outputList.add(output);
            }

            close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return outputList;
    }

    public void updateSmOrdersStatus(smOrders smorders) {
        Connection conn = null;
        // 주문번호
        try {
            conn = open();

            //주문 업데이트
            String sqlOrder = "UPDATE sm_orders SET SELLER_SEND_STATUS = ?  WHERE ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sqlOrder);


            pstmt.setString(1, String.valueOf(smorders.getStatus()));
            pstmt.setLong(2, smorders.getId());

            pstmt.executeUpdate();

            pstmt.close();
            close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//Sm_Orders_Update

    public smOrders findById(Long id) {
        Connection conn = null;
        smOrders smOrders = null;
        int orderId = 0;
        try {
            conn = open(); // ObjectDBIO open메소드 호출

            String sql = "SELECT * FROM SM_ORDERS WHERE ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                smOrders = new smOrders();
                smOrders.setId(id);// 주문 ID
                smOrders.setQuantity(rs.getInt("quantity")); // 주문 수량
                smOrders.setPaymentAmount(rs.getInt("payment_amount"));  //결제 금액
                smOrders.setCreatedAt(rs.getString("created_at"));  //주문 일자
                smOrders.setExpectedAt(rs.getString("expected_at"));  //예상 배송일
                String sellerSendStatusTemp = rs.getString("seller_send_status");
                System.out.println(sellerSendStatusTemp);
                smOrders.setStatus(SellerSendStatus.valueOf(sellerSendStatusTemp));  //판매자 발송 상태(배송준비중, 주문 취소, 배송완료)
                smOrders.setCustomerId(rs.getLong("customer_id"));  //고객 ID(FK)
                smOrders.setShoppingMallId(rs.getLong("shopping_mall_id"));  //쇼핑몰 ID(FK)
                smOrders.setProductId(rs.getLong("products_id")); //상품 ID(FK)
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return smOrders;
    }


    public void insertSmOrdersStatus(smOrders smorders) {
        Connection conn = null;
        // 주문번호
        try {
            //주문 업데이트
            String sqlOrder = "INSERT INTO sm_orders (ID, QUANTITY, PAYMENT_AMOUNT, CREATED_AT,  EXPECTED_AT, SELLER_SEND_STATUS, CUSTOMER_ID, SHOPPING_MALL_ID, PRODUCTS_ID ) VALUES (?, ?, ?, now(), now(), ?, ?, ?, ? )";
            PreparedStatement pstmt = conn.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS);
            // 주문 추가
//            pstmt.setString(1, String.valueOf(smorders.getStatus()));
            pstmt.setLong(1, smorders.getId());
            pstmt.setInt(2, smorders.getQuantity());
            pstmt.setInt(3, smorders.getPaymentAmount());
            pstmt.setString(4, String.valueOf(smorders.getStatus()));
            pstmt.setLong(5, smorders.getCustomerId());
            pstmt.setLong(6, smorders.getShoppingMallId());
            pstmt.setLong(7, smorders.getProductId());
            pstmt.executeUpdate();

            pstmt.close();
            close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//Sm_Orders_Update


}