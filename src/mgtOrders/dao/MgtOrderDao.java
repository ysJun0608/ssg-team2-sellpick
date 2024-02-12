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

            conn = open();
            /**
             *  발주 할 상품 입력
             *  상품 Id를 이용하여 등록
             **/

            // 발주관리 id 값 만들기
            String sqlCnt = "SELECT count(*) FROM mgt_orders";
            PreparedStatement psmtCnt = conn.prepareStatement(sqlCnt);
            int rows = psmtCnt.executeUpdate();
            String tempSerialNumb = String.format("%06", rows + 1);
            String tempId = createdAt + tempSerialNumb;
            Long id = Long.parseLong(tempId);

            while (true) {
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
                boolean result = psmt.execute();
                if (result == false) {
                    System.out.printf("발주 추가 실패하였습니다. 다시 입력 부탁드립니다.");
                    break;
                }
                mgtOrders.add(new MgtOrder(id, createdAt, purChaser, productId, quantity, false));
                id++;
            }

        } catch (IOException ioE) {
            ioE.printStackTrace();
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
        }

        return true;
    }


    // 발주등록(단건) 메뉴에서 발주된 주문을 확정
    public boolean conmfirmOrder() {
        try {
            String sql = "INSERT INTO mgt_orders VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement psmt = conn.prepareStatement(sql);

            for (MgtOrder order : mgtOrders) {
                order.setConfirm(true);
                psmt.setLong(1, order.getId());
                psmt.setString(2, order.getCreatedAt());
                psmt.setString(3, order.getPurchaser());
                psmt.setLong(4, order.getProductsId());
                psmt.setInt(5, order.getQuantity());
                // lombok 미작동, getter를 별도로 만들었으나 작동하지 않음. 수정필요
                psmt.setBoolean(6, true);
                boolean result = psmt.execute();
                if (result == false) {
                    System.out.printf("발주 확정이 실패하였습니다. 다시 입력 부탁드립니다.");
                    break;
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    // 발주등록(단건) 메뉴에서 발주된 주문을 확정 취소
    public boolean cencelOrder() {
        try {
            String sql = "INSERT INTO mgt_orders VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement psmt = conn.prepareStatement(sql);

            for (MgtOrder order : mgtOrders) {
                order.setConfirm(false);
                psmt.setLong(1, order.getId());
                psmt.setString(2, order.getCreatedAt());
                psmt.setString(3, order.getPurchaser());
                psmt.setLong(4, order.getProductsId());
                psmt.setInt(5, order.getQuantity());
                // lombok 미작동, getter를 별도로 만들었으나 작동하지 않음. 수정필요
                psmt.setBoolean(6, false);
                boolean result = psmt.execute();
                if (result == false) {
                    System.out.printf("발주 확정이 실패하였습니다. 다시 입력 부탁드립니다.");
                    break;
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
