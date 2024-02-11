package mgtOrders.dao;

import DBIO.ObjectDBIO;
import mgtOrders.domain.MgtOrder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class MgtOrderDao extends ObjectDBIO {
    Connection conn = null;

    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    ArrayList<MgtOrder> mgtOrders = new ArrayList<>();
    public boolean add() {
        try {
            /** 발주마스터 생성
             *  입력값 받기 (발주일자, 매입거래처)
             **/
            System.out.printf("발주마스터를 생성하기 위해 발주일자, 매입거래처 순으로 입력해주세요");
            System.out.printf("발주일자는 20240211형태로 입력해주세요");
            String createdAt = bufferedReader.readLine();
            String purChaser = bufferedReader.readLine();

            /**
             *  발주 할 상품 입력
             *  상품 Id를 이용하여 등록
             **/
            conn = open();

            // 발주관리 id 값 만들기
            String sqlCnt = "SELECT count(*) FROM mgt_orders";
            PreparedStatement psmtCnt = conn.prepareStatement(sqlCnt);
            int rows = psmtCnt.executeUpdate();
            String tempSerialNumb = String.format("%06", rows + 1);
            String tempId = createdAt + tempSerialNumb;
            Long id = Long.parseLong(tempId);

            while(true) {
                System.out.printf("발주할 상품 Id와 수량을 입력하세요. 입력이 완료되시면 exit를 입력하세요");
                String tempProductId = bufferedReader.readLine();
                if (tempProductId.equals("exit")) {
                    break;
                }
                Long productId = Long.parseLong(tempProductId);
                Integer quantity = Integer.parseInt(bufferedReader.readLine());

                String sql = "INSERT INTO mgt_orders VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement psmt = conn.prepareStatement(sql);

                psmt.setLong(1, id);
                psmt.setString(2, createdAt);
                psmt.setString(3, purChaser);
                psmt.setLong(4, productId);
                psmt.setInt(5, quantity);
                psmt.setBoolean(6, false);
                mgtOrders.add(new MgtOrder(id, createdAt, purChaser, productId, quantity, false));
                id++;
            }

            // MgtOrder mgtOrder = MgtOrder.builder().id(),;

        } catch(IOException ioE) {
            ioE.printStackTrace();
        } catch(SQLException sqlE) {
            sqlE.printStackTrace();
        }

        return true;
    }
}
