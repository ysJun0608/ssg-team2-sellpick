package delivery.dao;

import DBIO.ObjectDBIO;
import delivery.domain.Waybill;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;
public class WaybillDao extends ObjectDBIO {
    Scanner sc = new Scanner(System.in);
    Connection conn = null;
    public Long insertWaybill() {
        //주문 번호를 입력 -> 주문 테이블의 주문번호를 통해 운송장 테이블에 넣기
        conn = open();
        System.out.println("주문 번호를 입력해주세요: ");
        Long ordersNum = Long.parseLong(sc.nextLine());
        try{
            String ordersidsql = "INSERT INTO WAYBILL (ORDERS_ID) " +
                    "values(?);";

            //PreparedStatement 얻기 및 값 지정
            PreparedStatement pstmt = conn.prepareStatement(ordersidsql, Statement.RETURN_GENERATED_KEYS);
            //Statement 또는 PreparedStatement를 생성할 때 사용됩니다. 이 상수를 사용하면 데이터베이스에서 자동으로 생성된 키(예: 자동 증가하는 기본 키)를 검색할 수 있습니다.
            //일반적으로 데이터베이스에서 행을 삽입할 때, 자동으로 생성된 키(Generated Key)를 얻고자 할 때 사용

            // ? 주문번호
            pstmt.setLong(1, ordersNum);


            // sql문 실행
            int rows = pstmt.executeUpdate();
            System.out.println("주문번호가 담긴 운송장이 생성되었습니다.");

            // waybill_id(운송장 번호) 값 얻기  -> 이거 출고사용
            if (rows == 1){
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    long waybill_id = rs.getLong(1);
                   }
                rs.close();
                pstmt.close();
                close(conn);
            }
            System.out.println();

        }catch (Exception e){
            e.printStackTrace();
        }return ordersNum;

    }
    public void selectWaybill (Long ordersNum){
        conn = open();
        try{
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
                    "(SELECT wh.location FROM warehouse wh JOIN warehouse_section ws ON wh.id = ws.warehouse_id JOIN products p ON ws.id = p.wh_section_id JOIN sm_orders o ON p.id = o.products_id WHERE o.id = ?) AS warehouse_location, " + //상품테이블의 창고 id = 창고테이블의 창고 id
                    "(SELECT b.phone FROM business_owner b JOIN products p ON b.id = p.owner_id JOIN sm_orders o ON p.id = o.products_id WHERE o.id = ?) AS business_phone " +
                    "FROM waybill w " +
                    "WHERE w.orders_id = ? ";

            //PreparedStatement 얻기 및 값 지정=
            PreparedStatement pstmt = conn.prepareStatement(joinsql);


            // ?의 데이터 값은 모두 ordersNum
            for (int i = 1; i<=8; i++){
                pstmt.setLong(i, ordersNum);
            }
            pstmt.setLong(9, ordersNum);

            // sql문 실행
            ResultSet rs = pstmt.executeQuery();

            //보여줘 !
            while (rs.next()) {
                // 각 행에서 필요한 열 값을 가져와서 출력
                System.out.println("------------------------");
                System.out.println("운송장 번호: " + rs.getLong("id"));
                System.out.println("주문 번호: " + rs.getLong("orders_id"));
                System.out.println("상품 이름: " + rs.getString("product_name"));
                System.out.println("-- 보낸 이 --");
                System.out.println("쇼핑몰 이름: " + rs.getString("shopping_mall_name"));
                System.out.println("창고 주소: " + rs.getString("warehouse_location"));
                System.out.println("사업자 휴대폰 주소: " + rs.getString("business_phone"));
                System.out.println("-- 받는 이 --");
                System.out.println("고객 ID: " + rs.getLong("customer_id"));
                System.out.println("고객 이름: " + rs.getString("customer_name"));
                System.out.println("고객 핸드폰번호: " + rs.getString("customer_phone"));
                System.out.println("고객 주소: " + rs.getString("customer_address"));

                System.out.println("------------------------");
            }
            System.out.printf("주문번호 %d번의 운송장입니다.", ordersNum);

            rs.close();
            pstmt.close();
            close(conn);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateWaybill() {
        // TODO : implement
    }
}