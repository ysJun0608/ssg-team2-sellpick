package mgtOrders.dao;

import DBIO.ObjectDBIO;
import mgtOrders.domain.MgtOrder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;

public class MgtOrderDao extends ObjectDBIO {
    Connection conn = null;

    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    ArrayList<MgtOrder> mgtOrders = new ArrayList<>();

    public boolean add() {
        try {
            int id = 0;
            int productId = 0;
            int quantity = 0;
            // 발주마스터 생성
            System.out.printf("발주마스터를 생성하기 위해 매입거래처를 입력해주세요");
            String purChaser = bufferedReader.readLine();


            java.util.Date today = new java.util.Date();
            Date createdAt = new Date(today.getTime());

            conn = open();

            boolean result = false;

            while (!result) {
                String sql = "INSERT INTO mgt_orders VALUES (?, ?, ?, ?)";
                PreparedStatement psmt = conn.prepareStatement(sql);
                psmt.setString(1, null);
                psmt.setDate(2, createdAt);
                psmt.setString(3, purChaser);
                psmt.setString(4, "발주준비");
                result = psmt.execute();
                if (result == false) {
                    System.out.printf("발주마스터 생성에 실패하였습니다. 재생성합니다.");
                    break;
                }
            }

            // 발주마스터 id 가져오기
            Statement statement = conn.createStatement();

            String sqlQuery = "SELECT id FROM mgt_orders";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }

            resultSet.close();
            statement.close();

            // 발주 할 상품 입력 받기
            while (!result) {
                System.out.printf("발주할 상품 Id와 수량을 입력하세요. 입력이 완료되시면 exit를 입력하세요");
                String tempProductId = bufferedReader.readLine();
                if (tempProductId.equals("exit")) {
                    break;
                }
                productId = Integer.parseInt(tempProductId);
                quantity = Integer.parseInt(bufferedReader.readLine());

                String sql = "INSERT INTO mgt_orders_products_relationship VALUES (?, ?, ?, ?)";
                PreparedStatement psmt = conn.prepareStatement(sql);
                psmt.setString(1, null);
                psmt.setInt(2, quantity);
                psmt.setInt(3, productId);
                psmt.setInt(4, id);
                result = psmt.execute();
                if (result == false) {
                    System.out.printf("상품 입력에 실패하였습니다. 재입력해주세요.");
                    break;
                }
            }

            mgtOrders.add(new MgtOrder(id, purChaser, "발주준비", createdAt));

        } catch (IOException ioE) {
            ioE.printStackTrace();
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
        }

        return true;
    }


    // 발주등록(단건) 메뉴에서 발주된 주문을 확정
    public boolean confirmOrder() {
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
    public boolean cancelOrder() {
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
                    System.out.printf("발주확정 취소가 실패하였습니다. 다시 입력 부탁드립니다.");
                    break;
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }


}
