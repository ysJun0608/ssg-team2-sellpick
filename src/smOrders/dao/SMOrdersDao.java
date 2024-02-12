package smOrders.dao;

import DBIO.ObjectDBIO;
import smOrders.domain.SMOrders;
import smOrders.domain.ShoppingMall;

import java.sql.*;
import java.sql.Connection;
import java.util.Scanner;

public class SMOrdersDao extends ObjectDBIO {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        list();
    }

    //main 호출메소드
    public static void MainMenu() {
        SMOrdersDao dao = new SMOrdersDao();
        System.out.println("====================================================");
        System.out.println("메인메뉴: 1.Create  |  2.Read |  3.ReadAll(취소) |  4.ReadAll(배송준비중) |  5.Clear |  6.Exit   ");
        System.out.print("메뉴선택: ");
        String MainNum = sc.nextLine(); //메뉴 선택 입력 값
        switch (MainNum) { //MainNum ""
            case "1" -> dao.smOrdersInsert();
            case "2" -> dao.smOrdersRead();
            case "3" -> dao.smOrdersReadAllCanCel();
            case "4" -> dao.smOrdersReadAllPrepare();
            case "6" -> System.exit(0);
//          default -> list();
        }
    }
    //main 호출메소드
    public static void list() {
        MainMenu();
    }

    public void smOrdersUpdate(SMOrders smorders) {
        Connection conn = null;
        try {
            conn = open();

            // 고객 업데이트
            String sqlCustomer = "UPDATE CUSTOMER SET EMAIL = ?, PASSWORD = ?, NAME = ?, PHONE = ?, ADDRESS = ? WHERE ID = ?";
            PreparedStatement pstmtCustomer = conn.prepareStatement(sqlCustomer);
            pstmtCustomer.setString(1, "customer4@example.com");
            pstmtCustomer.setString(2, "password4");
            pstmtCustomer.setString(3, "Customer4");
            pstmtCustomer.setString(4, "123-456-7890");
            pstmtCustomer.setString(5, "Main St, City, Country");
            pstmtCustomer.setInt(6, 6010); // 고객 ID
            pstmtCustomer.executeUpdate();
            pstmtCustomer.close();

            // 쇼핑몰 업데이트
            String sqlShoppingMall = "UPDATE SHOPPING_MALL SET NAME = ? WHERE ID = ?";
            PreparedStatement pstmtShoppingMall = conn.prepareStatement(sqlShoppingMall);
            pstmtShoppingMall.setString(1, "온라인몰1");
            pstmtShoppingMall.setInt(2, 2010); // 쇼핑몰 ID
            pstmtShoppingMall.executeUpdate();
            pstmtShoppingMall.close();

            // 상품 업데이트
            String sqlProduct = "UPDATE PRODUCTS SET NAME = ?, STATUS = ?, COST = ?, PRICE = ?, BRAND_ID = ?, OWNER_ID = ?, WAREHOUSE_ID = ? WHERE ID = ?";
            PreparedStatement pstmtProduct = conn.prepareStatement(sqlProduct);
            pstmtProduct.setString(1, "상품1");
            pstmtProduct.setBoolean(2, true);
            pstmtProduct.setInt(3, 5000);
            pstmtProduct.setInt(4, 10000);
            pstmtProduct.setInt(5, 1); // 브랜드 ID
            pstmtProduct.setInt(6, 1); // 사업자 ID
            pstmtProduct.setInt(7, 1); // 창고 ID
            pstmtProduct.setInt(8, 2000); // 상품 ID
            pstmtProduct.executeUpdate();
            pstmtProduct.close();

            // 주문 업데이트
//            String sqlOrder = "UPDATE sm_orders SET QUANTITY = ?, PAYMENT_AMOUNT = ?, CREATED_AT = now(), EXPECTED_AT = now(), SELLER_SEND_STATUS = 'Yes', CUSTOMER_ID = ?, SHOPPING_MALL_ID = ?, PRODUCTS_ID = ? WHERE ID = ?";
//            PreparedStatement pstmtOrder = conn.prepareStatement(sqlOrder);
//            pstmtOrder.setInt(1, 100);
//            pstmtOrder.setInt(2, 1000);
//            pstmtOrder.setInt(3, 6010); // 고객 ID
//            pstmtOrder.setInt(4, 2010); // 쇼핑몰 ID
//            pstmtOrder.setInt(5, 2000); // 상품 ID
//            pstmtOrder.setInt(6, 1); // 주문 ID
//            pstmtOrder.executeUpdate();
//            pstmtOrder.close();

            //주문 업데이트
            String sqlOrder = "UPDATE sm_orders SET SELLER_SEND_STATUS = ?  WHERE ID = ?";
            PreparedStatement pstmtOrder = conn.prepareStatement(sqlOrder);

            //판매자 발송 상태
            System.out.print("판매자 발송 상태 (배송준비중, 주문 취소, 배송완료) : ");
            String seller_status = sc.nextLine();
            pstmtOrder.setString(1, seller_status );

            // 주문번호
            System.out.print("주문번호 : ");
            int order_id = Integer.parseInt(sc.nextLine());
            pstmtOrder.setInt(2, order_id);
            pstmtOrder.executeUpdate();
            pstmtOrder.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("-------------------------------------------------------");
        // 업데이트된 주문 조회 메소드 호출
        //Sm_Orders_Read();
        list();
    }//Sm_Orders_Update

    public void smOrdersInsert() {
        Connection conn = null;
        try {
            conn = open();

            // 고객 추가
            String sqlCustomer = "INSERT INTO CUSTOMER (ID, EMAIL, PASSWORD, NAME, PHONE, ADDRESS)  VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmtCustomer = conn.prepareStatement(sqlCustomer);
            pstmtCustomer.setInt(1, 7010);
            pstmtCustomer.setString(2, "customer4@example.com");
            pstmtCustomer.setString(3, "password4");
            pstmtCustomer.setString(4, "Customer4");
            pstmtCustomer.setString(5, "123-456-7890");
            pstmtCustomer.setString(6, "Main St, City, Country");
            pstmtCustomer.executeUpdate();
            pstmtCustomer.close();

            // 쇼핑몰 추가
            String sqlShoppingMall = "INSERT INTO SHOPPING_MALL (ID, NAME) VALUES (?, ?)";
            PreparedStatement pstmtShoppingMall = conn.prepareStatement(sqlShoppingMall);
            pstmtShoppingMall.setInt(1, 3010);
            pstmtShoppingMall.setString(2, "온라인몰1");
            pstmtShoppingMall.executeUpdate();
            pstmtShoppingMall.close();

            // 상품 추가
            String sqlProduct = "INSERT INTO PRODUCTS (ID, NAME, STATUS, COST, PRICE, BRAND_ID, OWNER_ID, WAREHOUSE_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmtProduct = conn.prepareStatement(sqlProduct);
            pstmtProduct.setInt(1, 3000);
            pstmtProduct.setString(2, "상품1");
            pstmtProduct.setBoolean(3, true);
            pstmtProduct.setInt(4, 5000);
            pstmtProduct.setInt(5, 10000);
            pstmtProduct.setInt(6, 1);
            pstmtProduct.setInt(7, 1);
            pstmtProduct.setInt(8, 1);
            pstmtProduct.executeUpdate();
            pstmtProduct.close();

//            // 주문 추가
//            String sqlOrder = "INSERT INTO sm_orders (ID, QUANTITY, PAYMENT_AMOUNT, CREATED_AT, EXPECTED_AT, SELLER_SEND_STATUS, CUSTOMER_ID, SHOPPING_MALL_ID, PRODUCTS_ID ) VALUES (NULL, ?, ?, now(), now(), 'Yes', ?, ?, ?)";
//            PreparedStatement pstmtOrder = conn.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS);
//            pstmtOrder.setInt(1, 100); //QUANTITY
//            pstmtOrder.setInt(2, 1000);//PAYMENT_AMOUNT
//            pstmtOrder.setInt(3, 7010);//CUSTOMER_ID
//            pstmtOrder.setInt(4, 3010);//SHOPPING_MALL_ID
//            pstmtOrder.setInt(5, 3000);//PRODUCTS_ID
//            pstmtOrder.executeUpdate();
//            pstmtOrder.close();

            // 주문 추가
            String sqlOrder = " INSERT INTO sm_orders (ID, QUANTITY, PAYMENT_AMOUNT, CREATED_AT,  EXPECTED_AT, SELLER_SEND_STATUS, CUSTOMER_ID, SHOPPING_MALL_ID, PRODUCTS_ID ) VALUES (NULL, ?, ?, now(), now(), ?, ?, ?, ? )";
            PreparedStatement pstmtOrder = conn.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS);
            System.out.print("주문 ID: ");
            String order_id = sc.nextLine();
            pstmtOrder.setString(1, order_id);

            System.out.print("주문 수량: ");
            String quantity = sc.nextLine();
            pstmtOrder.setString(2, quantity);
            //주문 일자 // 예상 배송일
            System.out.print("판매자 발송 상태(배송준비중, 주문 취소, 배송완료): ");
            String seller_send_status = sc.nextLine();
            pstmtOrder.setString(3, seller_send_status);

            System.out.print("고객 ID(FK): ");
            String customer_id = sc.nextLine();
            pstmtOrder.setString(4, customer_id);

            System.out.print("쇼핑몰 ID(FK): ");
            String shopping_mall_id = sc.nextLine();
            pstmtOrder.setString(5, shopping_mall_id);

            System.out.print("상품 ID(FK): ");
            String products_id = sc.nextLine();
            pstmtOrder.setString(6, products_id);
            pstmtOrder.executeUpdate();
            pstmtOrder.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("-------------------------------------------------------");
        // 추가한 주문 조회 메소드 호출
        list();
    }//smOrdersInsert


    // SM_ORDERS 테이블 전체조회 메소드
    public void smOrdersReadAll  () {

        Connection conn = null;
        SMOrders smorders = null;
//          int orderNo = 0; //기존 주문번호 변수 초기화

        try {
//              orderNo = Integer.parseInt(sc.nextLine()); // 기존 주문번호 입력값 input
//              conn = connectionBoard.open(); //기존
            conn = open(); // ObjectDBIO open메소드 호출

            String sql = "SELECT " +
                    "SOS.id, SOS.QUANTITY, " +
                    "SOS.PAYMENT_AMOUNT, " +
                    "SOS.CREATED_AT, " +
                    "SOS.EXPECTED_AT, " +
                    "SOS.SELLER_SEND_STATUS," +
                    " SOS.CUSTOMER_ID," +
                    " SOS.SHOPPING_MALL_ID," +
                    " SOS.PRODUCTS_ID," +
                    " SGM.name  \n" +
                    "FROM SM_ORDERS sos \n" +
                    "JOIN shopping_mall sgm \n" +
                    "ON sos.SHOPPING_MALL_ID = sgm.id ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
//              pstmt.setInt(1, orderNo); //input 세팅 값

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                // SM_ORDERS 주문 테이블
                smorders = new SMOrders();
//                    smorders.setId(orderNo); // 주문 id 기존
                smorders.setId(rs.getInt("id")); // 주문 id
                smorders.setQuantity(rs.getInt("quantity")); // 주문 수량
                smorders.setPayment_amount(rs.getInt("payment_amount"));  //결제 금액
                smorders.setCreated_at(rs.getDate("created_at"));  //주문 일자
                smorders.setExpected_at(rs.getDate("expected_at"));  //예상 배송일
                smorders.setSeller_send_status(rs.getString("seller_send_status"));  //판매자 발송 상태(배송준비중, 주문 취소, 배송완료)
                smorders.setCustomer_id(rs.getInt("customer_id"));  //고객 ID(FK)
                smorders.setShopping_mall_id(rs.getInt("shopping_mall_id"));  //쇼핑몰 ID(FK)
                smorders.setProducts_id(rs.getInt("products_id")); //상품 ID(FK)

                //SHOPPING_MALL 쇼핑 테이블
                ShoppingMall shoppingmall = new ShoppingMall();
                shoppingmall.setName(rs.getString("name")); //쇼핑몰이름

                System.out.println();
                System.out.println("[주문 관리]");
                // 주문 정보 출력
                System.out.println("주문번호: " + smorders.getId()); // 주문 번호 출력
                System.out.println("주문수량: " + smorders.getQuantity()); // 주문 수량
                System.out.println("결제금액: " + smorders.getPayment_amount()); // 결제 금액
//                  System.out.println(smorders);
                // 쇼핑몰 정보 출력
                System.out.println("쇼핑몰이름: " + shoppingmall.getName()); // 쇼핑몰이름
//                  System.out.println(shoppingmall);

                //ObjectDBIO 닫기
                close(conn);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        list();
    }//SmOrdersReadAll

    // SM_ORDERS 테이블 주문취소건 전체조회 메소드
    public void smOrdersReadAllCanCel() {

        Connection conn = null;
        SMOrders smorders = null;
//          int orderNo = 0; //기존 주문번호 변수 초기화

        try {
//              orderNo = Integer.parseInt(sc.nextLine()); // 기존 주문번호 입력값 input
//              conn = connectionBoard.open(); //기존
            conn = open(); // ObjectDBIO open메소드 호출

            String sql = "SELECT " +
                    "SOS.id, SOS.QUANTITY, " +
                    "SOS.PAYMENT_AMOUNT, " +
                    "SOS.CREATED_AT, " +
                    "SOS.EXPECTED_AT, " +
                    "SOS.SELLER_SEND_STATUS," +
                    " SOS.CUSTOMER_ID," +
                    " SOS.SHOPPING_MALL_ID," +
                    " SOS.PRODUCTS_ID," +
                    " SGM.name  \n" +
                    "FROM SM_ORDERS sos \n" +
                    "JOIN shopping_mall sgm \n" +
                    "ON sos.SHOPPING_MALL_ID = sgm.id \n" +
                    "WHERE  SOS.SELLER_SEND_STATUS = '주문취소' ";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                // SM_ORDERS 주문 테이블
                smorders = new SMOrders();
//               smorders.setId(orderNo); // 주문 id 기존
                smorders.setId(rs.getInt("id")); // 주문 id
                smorders.setQuantity(rs.getInt("quantity")); // 주문 수량
                smorders.setPayment_amount(rs.getInt("payment_amount"));  //결제 금액
                smorders.setCreated_at(rs.getDate("created_at"));  //주문 일자
                smorders.setExpected_at(rs.getDate("expected_at"));  //예상 배송일
                smorders.setSeller_send_status(rs.getString("seller_send_status"));  //판매자 발송 상태(배송준비중, 주문 취소, 배송완료)
                smorders.setCustomer_id(rs.getInt("customer_id"));  //고객 ID(FK)
                smorders.setShopping_mall_id(rs.getInt("shopping_mall_id"));  //쇼핑몰 ID(FK)
                smorders.setProducts_id(rs.getInt("products_id")); //상품 ID(FK)

                //SHOPPING_MALL 쇼핑 테이블
                ShoppingMall shoppingmall = new ShoppingMall();
                shoppingmall.setName(rs.getString("name")); //쇼핑몰이름

                System.out.println();
                System.out.println("[주문 관리]");
                // 주문 정보 출력
                System.out.println("주문번호: " + smorders.getId()); // 주문 번호 출력
                System.out.println("주문수량: " + smorders.getQuantity()); // 주문 수량
                System.out.println("결제금액: " + smorders.getPayment_amount()); // 결제 금액
                // 쇼핑몰 정보 출력
                System.out.println("쇼핑몰이름: " + shoppingmall.getName()); // 쇼핑몰이름

                close(conn);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        list();
    }//smOrdersReadAllCanCel

    // SM_ORDERS 테이블 배송준비중인건 전체조회 메소드
    public void smOrdersReadAllPrepare() {

        Connection conn = null;
        SMOrders smorders = null;
//          int orderNo = 0; //기존 주문번호 변수 초기화

        try {
//              orderNo = Integer.parseInt(sc.nextLine()); // 기존 주문번호 입력값 input
//              conn = connectionBoard.open(); //기존
            conn = open(); // ObjectDBIO open메소드 호출

            String sql = "SELECT " +
                    "SOS.id, SOS.QUANTITY, " +
                    "SOS.PAYMENT_AMOUNT, " +
                    "SOS.CREATED_AT, " +
                    "SOS.EXPECTED_AT, " +
                    "SOS.SELLER_SEND_STATUS," +
                    " SOS.CUSTOMER_ID," +
                    " SOS.SHOPPING_MALL_ID," +
                    " SOS.PRODUCTS_ID," +
                    " SGM.name  \n" +
                    "FROM SM_ORDERS sos \n" +
                    "JOIN shopping_mall sgm \n" +
                    "ON sos.SHOPPING_MALL_ID = sgm.id \n" +
                    "WHERE  SOS.SELLER_SEND_STATUS = '배송준비중' ";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                // SM_ORDERS 주문 테이블
                smorders = new SMOrders();
//               smorders.setId(orderNo); // 주문 id 기존
                smorders.setId(rs.getInt("id")); // 주문 id
                smorders.setQuantity(rs.getInt("quantity")); // 주문 수량
                smorders.setPayment_amount(rs.getInt("payment_amount"));  //결제 금액
                smorders.setCreated_at(rs.getDate("created_at"));  //주문 일자
                smorders.setExpected_at(rs.getDate("expected_at"));  //예상 배송일
                smorders.setSeller_send_status(rs.getString("seller_send_status"));  //판매자 발송 상태(배송준비중, 주문 취소, 배송완료)
                smorders.setCustomer_id(rs.getInt("customer_id"));  //고객 ID(FK)
                smorders.setShopping_mall_id(rs.getInt("shopping_mall_id"));  //쇼핑몰 ID(FK)
                smorders.setProducts_id(rs.getInt("products_id")); //상품 ID(FK)

                //SHOPPING_MALL 쇼핑 테이블
                ShoppingMall shoppingmall = new ShoppingMall();
                shoppingmall.setName(rs.getString("name")); //쇼핑몰이름

                System.out.println();
                System.out.println("[주문 관리]");
                // 주문 정보 출력
                System.out.println("주문번호: " + smorders.getId()); // 주문 번호 출력
                System.out.println("주문수량: " + smorders.getQuantity()); // 주문 수량
                System.out.println("결제금액: " + smorders.getPayment_amount()); // 결제 금액
                // 쇼핑몰 정보 출력
                System.out.println("쇼핑몰이름: " + shoppingmall.getName()); // 쇼핑몰이름

                close(conn);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        list();
    }//smOrdersReadAllPrepare

    // SM_ORDERS 테이블 하나조회 메소드
    public void smOrdersRead() {
        Connection conn = null;
        SMOrders smorders = null;
        int order_id = 0;
        try {
            System.out.print("주문 번호를 입력해주세요 : ");
            order_id = Integer.parseInt(sc.nextLine());
            conn = open(); // ObjectDBIO open메소드 호출

            String sql = "SELECT * FROM SM_ORDERS WHERE ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, order_id);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                smorders = new SMOrders();
                smorders.setId(order_id);// 주문 ID
                smorders.setQuantity(rs.getInt("quantity")); // 주문 수량
                smorders.setPayment_amount(rs.getInt("payment_amount"));  //결제 금액
                smorders.setCreated_at(rs.getDate("created_at"));  //주문 일자
                smorders.setExpected_at(rs.getDate("expected_at"));  //예상 배송일
                smorders.setSeller_send_status(rs.getString("seller_send_status"));  //판매자 발송 상태(배송준비중, 주문 취소, 배송완료)
                smorders.setCustomer_id(rs.getInt("customer_id"));  //고객 ID(FK)
                smorders.setShopping_mall_id(rs.getInt("shopping_mall_id"));  //쇼핑몰 ID(FK)
                smorders.setProducts_id(rs.getInt("products_id")); //상품 ID(FK)
                System.out.println(smorders);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (smorders != null) {

            System.out.println("\n------------------------------------------------------\n");
            System.out.println("보조 메뉴: 1. Update | 2. Delete | 3. List  | 4. 메인메뉴로 돌아가기");
            System.out.print("메뉴 선택: ");
            String subMenuNum = sc.nextLine();

            switch (subMenuNum) {
                case "1" -> smOrdersUpdate(smorders); //Read 선행이 필요함
//                case "2" -> smOrdersUpdate(bno);
                case "3" -> smOrdersReadAll();
                case "4" -> list();
            }
        }
        list();
    }//smOrdersRead


}
