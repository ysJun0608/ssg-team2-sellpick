package inventory.dao;

import DBIO.ObjectDBIO;
import inventory.domain.Type;
import inventory.domain.Warehouse;
import smOrders.domain.ShoppingMall;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class WarehouseDao extends ObjectDBIO {

    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    Connection conn = null;


    // TODO : implement
    // 창고 생성메소드
    public Warehouse createwarehouse(ShoppingMall id) {
        Warehouse warehouse = new Warehouse();
        try {
            open();
            String sql = "insert into warehouse(type,location) values(?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            System.out.println("창고가 있는 지역을 입력해주세요");
            String location = input.readLine();
            System.out.println("창고 타입을 숫자로 입력해주세요");
            System.out.println("1. WET | 2. DRY");
            int num = Integer.parseInt(input.readLine());
            if (num == 1) {
                warehouse.setType(Type.WET);
            } else if (num == 2) {
                warehouse.setType(Type.DRY);
            } else {
                System.out.println("잘못된 번호를 입력하셨습니다.");
            }
            warehouse.setLocation(location);
            pstmt.setString(1, warehouse.getType().toString());
            pstmt.setString(2, location);
            pstmt.executeUpdate();
            pstmt.close();
        close(conn);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return warehouse;}
    //창고 변경 메소드
    public Warehouse updateWarehouse(ShoppingMall shoppingMall) {
        Warehouse warehouse = new Warehouse();
        try {
            open();
            String sql = "UPDATE warehouse SET type = ?, location = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                System.out.println("창고 타입을 숫자로 입력해주세요");
                System.out.println("1. WET | 2. DRY");
                int num = Integer.parseInt(input.readLine());

                if (num == 1) {
                    warehouse.setType(Type.WET);
                } else if (num == 2) {
                    warehouse.setType(Type.DRY);
                } else {
                    System.out.println("잘못된 번호를 입력하셨습니다. 돌아갑니다");
                    return warehouse; // 유효하지 않은 입력이면 메소드 종료
                }

                pstmt.setString(1, warehouse.getType().toString());
                System.out.println("창고가 있는 지역을 입력해주세요");
                String location = input.readLine();
                pstmt.setString(2, location);
                pstmt.setLong(3, shoppingMall.getId());
                pstmt.executeUpdate();
                warehouse.setLocation(location);
            }
            close(conn);
        } catch (SQLException | IOException | NumberFormatException e) {
            e.printStackTrace(); // 실제 상황에 따라 로깅 등으로 변경
        }
        return warehouse;
    }


}
