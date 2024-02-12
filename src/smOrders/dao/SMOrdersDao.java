package smOrders.dao;

import DBIO.ObjectDBIO;
import smOrders.domain.SMOrders;
import smOrders.domain.ShoppingMall;
import java.sql.*;
import java.sql.Connection;
import java.util.Scanner;

public class SMOrdersDao  extends  ObjectDBIO {
    static  Scanner sc = new Scanner(System.in);
//    private Connection conn = null;
    // 데이터베이스 연결 메서드

    public  void create() {
        Connection conn = null;
        try {
            conn = open();

            //매개변수화된 SQL문
            String sql = "INSERT INTO sm_orders (id, shopping_mall_id, products_id, customer_id,  payment_amount) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); //AUTO_INCREMENT

            System.out.print("주문 id: ");
            String id = sc.nextLine();
            pstmt.setString(1, id);

            System.out.print("쇼핑몰 id: ");
            String shopping_mall_id = sc.nextLine();
            pstmt.setString(2, shopping_mall_id);

            System.out.print("상품_id: ");
            String products_id = sc.nextLine();
            pstmt.setString(3, products_id);

            System.out.print("고객_id: ");
            String customer_id = sc.nextLine();
            pstmt.setString(4, customer_id);

            System.out.print("결제 금액: ");
            String payment_amount = sc.nextLine();
            pstmt.setString(5, payment_amount);

            //SQL문 실행
            int i = pstmt.executeUpdate();

            //bno 값 얻기
            if (i == 1) {
                ResultSet rs = pstmt.getGeneratedKeys(); //bno 컬럼d의 값을 리턴받는다.
                if (rs.next()) {
                    int bno = rs.getInt(1);
                    System.out.println("bno:" + bno);
                }
                rs.close();
            }
            //PreparedStatement 닫기
            pstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    //연결 끊기
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("-------------------------------------------------------");
//        list();
        read();

    }//create


    public void read() {

            Connection conn = null;
            int orderNo = 0;
            SMOrders smorders = null;

            try {
                System.out.print("주문 번호 10번입력해주세요 : ");
//                orderNo = Integer.parseInt(sc.nextLine());
//                conn = connectionBoard.open();
                conn = open();
//                String sql = "select * from sm_orders where id = ?";
//                String sql = "select * from sm_orders ";
                String sql = "select sm.id , sm.shopname  from sm_orders so join shopping_mall sm on so.shopping_mall_id =  sm.id ";
                PreparedStatement pstmt = conn.prepareStatement(sql);
//                pstmt.setInt(1, orderNo);

                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    smorders = new SMOrders();
                    smorders.setId(orderNo); // 주문 id
                    smorders.setShopping_mall_id(rs.getInt("shopping_mall_id")); // 쇼핑몰 id
                    smorders.setProducts_id(rs.getInt("products_id"));  //상품_id
                    smorders.setCustomer_id(rs.getInt("customer_id"));  //고객_id
                    smorders.setCREATED_AT(rs.getDate("CREATED_AT"));  //주문일
                    smorders.setExpected_at(rs.getDate("Expected_at"));  //발송예정일
                    smorders.setAmount(rs.getInt("amount"));  //상품수량
                    smorders.setPayment_amount(rs.getInt("payment_amount"));  //결제 금액
                    smorders.setSeller_send_statis(rs.getString("seller_send_statis")); //판매자발송확인

                    ShoppingMall shoppingmall = new ShoppingMall();
                    shoppingmall.setName(rs.getString("shopname")); //쇼핑몰이름



                    System.out.println();
                    System.out.println(smorders);
                    System.out.println(shoppingmall);
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
//        close(conn);
    }//read






}
