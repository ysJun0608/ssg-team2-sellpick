package smOrders.dao;

import DBIO.ObjectDBIO;
import smOrders.domain.smOrders;
import smOrders.dto.SmOrdersAllOutput;
import smOrders.dto.SmOrdersOutput;
import smOrders.enums.SellerSendStatus;

import java.sql.*;
import java.util.*;

public class SMOrdersDao extends ObjectDBIO {

    static Scanner sc = new Scanner(System.in);

    Connection conn = null;

    //주문 전체 조회
    /**
     * DB에서 취소된 주문 정보를 조회하여 반환합니다.
     *
     * @return 취소된 주문 목록
     */
    public List<SmOrdersOutput> smOrdersReadAll() {
        List<SmOrdersOutput> outputList = new ArrayList<>();

        try {
            conn = open(); // ObjectDBIO open 메소드 호출

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
                    "WHERE SO.SHOPPING_MALL_ID IN (SELECT ID FROM SHOPPING_MALL) " +
                    "ORDER BY SO.ID DESC";

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

    /**
     * DB에서 배송 준비 중인 주문 정보를 조회하여 반환합니다.
     *
     * @return 배송 준비 중인 주문 목록
     */
    public List<SmOrdersOutput> readAllPreparedOrders() {

        List<SmOrdersOutput> outputList = new ArrayList<>();

        try {
            conn = open(); // ObjectDBIO open 메소드 호출

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

    /**
     * DB에서 취소된 주문 정보를 조회하여 반환합니다.
     *
     * @return 취소된 주문 목록
     */
    public List<SmOrdersOutput> readAllCanceledOrders() {

        List<SmOrdersOutput> outputList = new ArrayList<>();

        try {
            conn = open(); // ObjectDBIO open 메소드 호출

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

    /**
     * 주문 상태를 업데이트합니다.
     *
     * @param smorders 업데이트할 주문 정보
     */
    public void updateSmOrdersStatus(smOrders smorders) {


        try {
            conn = open();

            String sqlOrder = "UPDATE SM_ORDERS SET SELLER_SEND_STATUS = ?  WHERE ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sqlOrder);

            pstmt.setString(1, String.valueOf(smorders.getStatus()));
            pstmt.setLong(2, smorders.getId());

            pstmt.executeUpdate();

            pstmt.close();
            close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 주어진 ID에 해당하는 주문을 조회합니다.
     *
     * @param id 조회할 주문 ID
     * @return 조회된 주문 객체
     */
    public smOrders findById(Long id) {
        smOrders smOrders = null;

        try {
            conn = open();

            String sql = "SELECT * FROM SM_ORDERS WHERE ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                smOrders = new smOrders();
                smOrders.setId(id);
                smOrders.setQuantity(rs.getInt("quantity"));
                smOrders.setPaymentAmount(rs.getInt("payment_amount"));
                smOrders.setCreatedAt(rs.getString("created_at"));
                smOrders.setExpectedAt(rs.getString("expected_at"));
                String sellerSendStatusTemp = rs.getString("seller_send_status");
                smOrders.setStatus(SellerSendStatus.valueOf(sellerSendStatusTemp));
                smOrders.setCustomerId(rs.getLong("customer_id"));
                smOrders.setShoppingMallId(rs.getLong("shopping_mall_id"));
                smOrders.setProductId(rs.getLong("products_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return smOrders;
    }

    /**
     * 주문 정보를 삽입합니다.
     *
     * @param smorders 삽입할 주문 정보
     */
    public void insertSmOrdersStatus(smOrders smorders) {

        try {
            // Connection 연결 후 open 호출
            conn = open();

            // SQL문 작성
            String ProductPrice = "SELECT PRICE FROM PRODUCTS WHERE ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(ProductPrice);
            pstmt.setLong(1, smorders.getProductId());
            ResultSet rs = pstmt.executeQuery();
            int price = 0;
            while (rs.next()) {
                price = rs.getInt("price");
            }

            String sqlOrder = "INSERT INTO SM_ORDERS (QUANTITY, PAYMENT_AMOUNT, CREATED_AT,  EXPECTED_AT, SELLER_SEND_STATUS, CUSTOMER_ID, SHOPPING_MALL_ID, PRODUCTS_ID ) VALUES (?, ?, now(), now(), ?, ?, ?, ? )";
            pstmt = conn.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS);


            pstmt.setInt(1, smorders.getQuantity());
            pstmt.setInt(2, smorders.getQuantity() * price);
            pstmt.setString(3, String.valueOf(smorders.getStatus()));
            pstmt.setLong(4, smorders.getCustomerId());
            pstmt.setLong(5, smorders.getShoppingMallId());
            pstmt.setLong(6, smorders.getProductId());
            pstmt.executeUpdate();

            pstmt.close();
            close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SmOrdersAllOutput readOneAlloutput(Long id) {
        SmOrdersAllOutput smordersalloutput = null;

        SmOrdersAllOutput salloutput = null;
        try {
            conn = open(); // ObjectDBIO open 메소드 호출

            String sql = "SELECT \n" +
                    "    SO.ID as order_id,\n" +
                    "    SO.QUANTITY,\n" +
                    "    SO.PAYMENT_AMOUNT,\n" +
                    "    SO.CREATED_AT ,\n" +
                    "    SO.EXPECTED_AT ,\n" +
                    "    SO.SELLER_SEND_STATUS,\n" +
                    "    SO.SHOPPING_MALL_ID,\n" +
                    "    SGM.NAME as SHOPPING_NAME,\n" +
                    "    SO.PRODUCTS_ID,\n" +
                    "    PDS.NAME as PRODUCTS_NAME,\n" +
                    "    BAD.NAME as BRAND_NAME\n" +
                    "FROM SM_ORDERS SO\n" +
                    "JOIN SHOPPING_MALL SGM ON SO.SHOPPING_MALL_ID = SGM.ID\n" +
                    "JOIN CUSTOMER CTR ON SO.CUSTOMER_ID = CTR.ID\n" +
                    "JOIN PRODUCTS PDS ON SO.PRODUCTS_ID = PDS.ID\n" +
                    "JOIN BUSINESS_OWNER BSOR ON PDS.OWNER_ID = BSOR.ID\n" +
                    "JOIN WAREHOUSE_SHOPPING_MALL_RELATIONSHIP WSMR ON WSMR.ID = SO.SHOPPING_MALL_ID -- 창고\n" +
                    "JOIN BRAND BAD ON BAD.ID = PDS.BRAND_ID\n" +
                    "JOIN WAREHOUSE WHE ON WSMR.WAREHOUSE_ID = WHE.ID\n" +
                    "AND SO.ID = ? \n" +
                    "WHERE SO.SHOPPING_MALL_ID IN (SELECT ID FROM SHOPPING_MALL)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                salloutput = new SmOrdersAllOutput(
                        rs.getLong("order_id"),
                        rs.getInt("quantity"),
                        rs.getInt("payment_amount"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("expected_at").toLocalDateTime(),
                        rs.getString("seller_send_status"),
                        rs.getLong("shopping_mall_id"),
                        rs.getString("shopping_name"),
                        rs.getLong("products_id"),
                        rs.getString("products_name"),
                        rs.getString("brand_name")
                );
            }

            close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salloutput;
    }
}