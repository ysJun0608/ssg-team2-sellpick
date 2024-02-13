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

    int id = 1;

    public boolean add() {
        try {

            int productId = 0;
            int quantity = 0;
            // 발주마스터 생성
            System.out.printf("발주마스터를 생성하기 위해 매입거래처를 입력해주세요 : ");
            String purChaser = bufferedReader.readLine();


            java.util.Date today = new java.util.Date();
            java.sql.Timestamp createdAt = new java.sql.Timestamp(today.getTime());

            conn = open();

            boolean result = false;

            while (!result) {
                String sql = "INSERT INTO mgt_orders(PURCHASER, STATUS, CREATED_AT) VALUES (?, ?, ?)";
                PreparedStatement psmt = conn.prepareStatement(sql);
                psmt.setString(1, purChaser);
                psmt.setString(2, "READY");
                psmt.setTimestamp(3, createdAt);
                int row = psmt.executeUpdate();
                if (row == 1) {
                    result = true;
                }
                if (!result) {
                    System.out.println("발주마스터 생성에 실패하였습니다. 재생성합니다.");
                }
            }

            // 발주마스터 id 가져오기
            Statement statement = conn.createStatement();

            String sqlQuery = "SELECT id FROM mgt_orders";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                id = resultSet.getInt("ID");
            }

            resultSet.close();
            statement.close();

            // 발주 할 상품 입력 받기
            while (true) {
                System.out.println("상품을 입력하겠습니다. 입력이 완료되시면 상품 Id에 exit를 입력하세요.");
                System.out.printf("발주할 상품 Id를 입력하세요. : ");
                String tempProductId = bufferedReader.readLine();
                if (tempProductId.equals("exit")) {
                    break;
                }
                System.out.printf("수량를 입력하세요. : ");
                productId = Integer.parseInt(tempProductId);
                quantity = Integer.parseInt(bufferedReader.readLine());

                String sql = "INSERT INTO mgt_orders_products_relationship(QUANTITY, PRODUCTS_ID, MGT_ORDERS_ID) VALUES (?, ?, ?)";
                PreparedStatement psmt = conn.prepareStatement(sql);
                psmt.setInt(1, quantity);
                psmt.setInt(2, productId);
                psmt.setInt(3, id);
                psmt.executeUpdate();

            }
            MgtOrder tempOrder = new MgtOrder(id, purChaser, "READY", createdAt);
            mgtOrders.add(tempOrder);

            conn.close();
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
            conn = open();
            System.out.print("주문을 확정하시겠습니까 ? y/n : ");
            String temp = bufferedReader.readLine();
            System.out.println(temp);
            if (temp.equals("y") || temp.equals("Y")) {
                String sql = "UPDATE mgt_orders SET STATUS = ? WHERE id = ?";
                PreparedStatement psmt = conn.prepareStatement(sql);
                psmt.setString(1, "DONE");
                psmt.setInt(2, id);
                int row = psmt.executeUpdate();
                System.out.println(row);
                if (row == 1) {
                    System.out.println("발주가 확정되었습니다.");
                } else {
                    System.out.println("등록된 발주가 없습니다.");
                }
            } else {
                System.out.println("발주 확정 메뉴를 종료합니다.");
            }
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException Ie) {
            Ie.printStackTrace();
        }
        return true;
    }


    // 발주등록(단건) 메뉴에서 발주된 주문을 확정 취소
    public boolean cancelOrder() {
        try {
            conn = open();
            System.out.print("확정된 주문을 취소하시겠습니까 ? y/n : ");
            String temp = bufferedReader.readLine();
            System.out.println(temp);
            if (temp.equals("y") || temp.equals("Y")) {
                String sql = "UPDATE mgt_orders SET STATUS = '?' WHERE id = ?";
                PreparedStatement psmt = conn.prepareStatement(sql);
                psmt.setString(1, "READY");
                psmt.setInt(2, id);
                int row = psmt.executeUpdate();
                System.out.println(row);
                if (row == 1) {
                    System.out.println("확정된 발주를 취소했습니다.");
                } else {
                    System.out.println("발주 확정이 실패했습니다.");
                }
            } else {
                System.out.println("확정취소 메뉴를 종료합니다.");
            }

            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException Ie) {
            Ie.printStackTrace();
        }
        return true;
    }


    // 확정되지 않는 발주목록들을 여러개 선택하여 일괄 처리
    public void confirmList() {
        ArrayList<Integer> sellectNum = new ArrayList<>();

        try {
            conn = open();
            System.out.println("확정되지 않은 발주 목록입니다.");

            for (MgtOrder mgtOrder : mgtOrders) {
                    print(mgtOrder);
                if (mgtOrder.getStatus().equals("READY")) {
                }
            }
            System.out.println("확정시킬 발주목록들을 하나씩 입력해주세요.");
            System.out.println("선택이 끝나면 0를 입력해주세요");
            while (true) {
                int input = Integer.parseInt(bufferedReader.readLine());
                if (input == 0) {
                    break;
                }
                sellectNum.add(input);
            }

            String sql = "UPDATE mgt_orders SET STATUS = ? WHERE id = ?";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, "DONE");
            for (int num : sellectNum) {
                psmt.setInt(2, num);
                psmt.executeUpdate();
            }
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException Ie) {
            Ie.printStackTrace();
        }
    }


    // 발주 목록을 조회
    public void search() {

        System.out.println("조회할 날짜를 입력하세요");
        ArrayList<MgtOrder> searchList = new ArrayList<>();
        try {
            conn = open();
            System.out.print("시작일(예시 : 20240213) : ");
            String startDate = bufferedReader.readLine();

            System.out.println();

            System.out.print("종료일(예시 : 20240213) : ");
            String endDate = bufferedReader.readLine();


            String searchQuery = "SELECT * FROM mgt_orders WHERE CREATED_AT BETWEEN ? and ?+0.999999999";
            PreparedStatement searchPsmt = conn.prepareStatement(searchQuery);
            searchPsmt.setString(1, startDate);
            searchPsmt.setString(2, endDate);
            ResultSet resultSet = searchPsmt.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String purchaser = resultSet.getString("PURCHASER");
                String tempStatus = resultSet.getString("STATUS");
                Timestamp date = resultSet.getTimestamp("CREATED_AT");
                MgtOrder mgtOrder = new MgtOrder(id, purchaser, tempStatus, date);
                searchList.add(mgtOrder);
            }
            resultSet.close();
            searchPsmt.close();

            printList(searchList);
            conn.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException Se) {
            Se.printStackTrace();
        }
    }


    public void searchNonDelived() {

        ArrayList<MgtOrder> searchList = new ArrayList<>();
        try {
            conn = open();
            System.out.print("조회할 날짜를 입력하세요(예시 : 20240213) : ");
            String searchDate = bufferedReader.readLine();
            System.out.println();

            String searchQuery = "SELECT * FROM mgt_orders WHERE STATUS != ? AND CREATED_AT BETWEEN ? and ?+0.999999999";
            PreparedStatement searchPsmt = conn.prepareStatement(searchQuery);
            searchPsmt.setString(1, "DELIVERED");
            searchPsmt.setString(2, searchDate);
            searchPsmt.setString(3, searchDate);
            ResultSet resultSet = searchPsmt.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String purchaser = resultSet.getString("PURCHASER");
                String tempStatus = resultSet.getString("STATUS");
                Timestamp date = resultSet.getTimestamp("CREATED_AT");
                MgtOrder mgtOrder = new MgtOrder(id, purchaser, tempStatus, date);
                searchList.add(mgtOrder);
            }

            resultSet.close();
            searchPsmt.close();

            printList(searchList);
            conn.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException Se) {
            Se.printStackTrace();
        }
    }

    private void print(MgtOrder mgtOrder) {
        System.out.println(mgtOrder.toString());
    }

    private void printList(ArrayList<MgtOrder> mgtOrders) {
        for (MgtOrder mgtOrder : mgtOrders) {
            System.out.println(mgtOrder.toString());
        }
    }
}
