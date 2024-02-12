package inventory.dao;

import DBIO.ObjectDBIO;
import delivery.domain.DeliveryCmp;
import inventory.domain.WarehouseType;
import inventory.domain.Warehouse;
import inventory.service.WarehouseService;
import smOrders.domain.ShoppingMall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WarehouseDao extends ObjectDBIO {

    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    Connection conn = null;
    ArrayList<Warehouse> warehouses = new ArrayList<>();

    // TODO : implement
    public void DeliverycmpRead() {
        try {
            conn = open();
            /**
             * 택배사 데이터 가져와서 창고와 연동할 데이터 추가하는 코드
             */
            ArrayList<DeliveryCmp> deliveryCmps = new ArrayList<>();
            String selectDelivery_cmp = "select id, name from delivery_cmp";
            PreparedStatement pstmtD = conn.prepareStatement(selectDelivery_cmp);
            ResultSet dcRs = pstmtD.executeQuery();
            while (dcRs.next()) {
                Long id = dcRs.getLong("id");
                String name = dcRs.getString("name");
                DeliveryCmp dc = new DeliveryCmp();
                dc.setId(id);
                dc.setName(name);
                deliveryCmps.add(dc);
            }
            dcRs.close();
            pstmtD.close();
            for (DeliveryCmp deliveryCmp : deliveryCmps) {
                System.out.println(deliveryCmp.toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 창고 생성하고 창고 쇼핑몰관계 테이블에도 한번에 데이터 넣는 메소드
     */
    public Warehouse createWarehouse() {
        Warehouse warehouse = new Warehouse();
        try {
            conn = open();
            /**
             * 택배사 데이터 가져와서 창고와 연동할 데이터 추가하는 코드
             */
            DeliverycmpRead();
            System.out.println("창고에서 사용할 택배사번호를 입력해주세요");
            Long chooseDcId = Long.valueOf(input.readLine());

            /**
             * 창고 생성하는 기능
             */
            String insertsql = "insert into warehouse(type,location,DELIVERY_CMP_ID) values(?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(insertsql, Statement.RETURN_GENERATED_KEYS);
            System.out.println("창고가 있는 지역을 입력해주세요");
            String location = input.readLine();
            System.out.println("창고 타입을 숫자로 입력해주세요");
            System.out.println("1. WET | 2. DRY");
            int num = Integer.parseInt(input.readLine());
            if (num == 1) {
                warehouse.setType(WarehouseType.WET);
            } else if (num == 2) {
                warehouse.setType(WarehouseType.DRY);
            } else {
                System.out.println("잘못된 번호를 입력하셨습니다.");
            }
            warehouse.setLocation(location);
            pstmt.setString(1, warehouse.getType().toString());
            pstmt.setString(2, location);
            pstmt.setLong(3, chooseDcId);

            int rows = pstmt.executeUpdate();
            /**
             *  warehouse pk 가져오기
             */
            Long warehousePk = null;
            if (rows == 1) {
                ResultSet rs = pstmt.getGeneratedKeys();
                while (rs.next()) {
                    warehousePk = rs.getLong(1);
                }
            }
            pstmt.close();
            String selectShoppimallsql = "select id, name from shopping_mall";
            PreparedStatement pstmt3 = conn.prepareStatement(selectShoppimallsql);
            ResultSet smRs = pstmt3.executeQuery();
            List<ShoppingMall> smList = new ArrayList<>();
            while (smRs.next()) {
                Long id = smRs.getLong("id");
                String name = smRs.getString("name");
                ShoppingMall sm = new ShoppingMall();
                sm.setId(id);
                sm.setName(name);
                smList.add(sm);
            }
            smRs.close();
            pstmt3.close();
            for (ShoppingMall sm : smList) {
                System.out.println(sm.toString());
            }
            System.out.println("쇼핑몰 번호를 입력해주세요");
            Long chooseId = Long.valueOf(input.readLine());

            /**
             *  가져온 warehousePk 관계table에 집어 넣기
             */
            String insertToRelationshipsql = "insert into warehouse_shopping_mall_relationship(WAREHOUSE_ID,SM_ID) values (?,?)";
            PreparedStatement pstmt2 = conn.prepareStatement(insertToRelationshipsql, Statement.RETURN_GENERATED_KEYS);
            pstmt2.setLong(1, warehousePk);
            pstmt2.setLong(2, chooseId);
            int row = pstmt2.executeUpdate();
            if (row == 1) {
                System.out.println("창고생성및 쇼핑몰과의 연동이 완료되었습니다.");

            }
            pstmt2.close();
            close(conn);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return warehouse;
    }

    //창고확인 메소드

    /**
     * 1.전체 warehouse를 보여준다
     * 2.보여주고 난 select 된 창고를 넘겨준다
     */
    public Warehouse warehouseRead() {
        conn = open();
        Warehouse warehouse = new Warehouse();
        try {
            String selectall = "select * from warehouse";
            PreparedStatement pstmt = conn.prepareStatement(selectall);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Warehouse rows = new Warehouse();
                rows.setId(Long.valueOf(rs.getLong("ID")));
                rows.setDelivery_id(Long.valueOf(rs.getString("DELIVERY_CMP_ID")));
                rows.setType(WarehouseType.valueOf(rs.getString("TYPE")));
                rows.setLocation(rs.getString("LOCATION"));
                System.out.println(rows.toString());
                warehouses.add(rows);
            }
            //  String selectOne = "select * from warehouse where id = ?";
            // PreparedStatement pstmt1 = conn.prepareStatement(selectOne);
            System.out.println("불러오고싶은 창고의 id를 입력해주세요");
            Long id = Long.parseLong(input.readLine());
            // pstmt1.setLong(1,id);
            for (Warehouse w : warehouses) {
                if (w.getId() == id) {

                    warehouse.setId(w.getId());
                    warehouse.setDelivery_id(w.getDelivery_id());
                    warehouse.setType(w.getType());
                    warehouse.setLocation(w.getLocation());
                }
            }
            rs.close();
            pstmt.close();
            close(conn);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        System.out.println(warehouse.toString());
        selectMenu(warehouse);
        return warehouse;
    }

    public Warehouse selectMenu(Warehouse warehouse) {
        try {
            System.out.println("변경하고자하는 창고의 세부사항을 입력해주세요");
            System.out.println("1. 택배사 변경 | 2. 전체변경");
            String choosenum = input.readLine();
            switch (choosenum) {
                case "1" -> UpdateDELIVERY_CMP_ID(warehouse);
                case "2" -> updateWarehouse(warehouse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return warehouse;
    }

    public void UpdateDELIVERY_CMP_ID(Warehouse warehouse) {
        conn = open();
        DeliverycmpRead();
        try {
            Long id = warehouse.getId();
            String sql = "UPDATE warehouse SET DELIVERY_CMP_ID = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            System.out.println("창고에서 사용할 택배사번호를 입력해주세요");
            Long chooseDcId = Long.valueOf(input.readLine());
            pstmt.setLong(1, chooseDcId);
            pstmt.setLong(2, id);
            int rows = pstmt.executeUpdate();
            if (rows == 1) {
                System.out.println("변경이 완료되었습니다.");
            }
            pstmt.close();
            close(conn);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    //창고 변경 메소드
    //1. 추후 리펙토링할것
    // 택배사 변경 -> 택배사 다 긁어와서 보여주고 변경할것
    // 타입변경
    // 장소 변경
    public Warehouse updateWarehouse(Warehouse warehouse) {
        try {
            conn = open();
            DeliverycmpRead();
            String sql = "UPDATE warehouse SET type = ?, location = ?,DELIVERY_CMP_ID = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

                Long id = warehouse.getId();

                System.out.println("창고에서 사용할 택배사번호를 입력해주세요");
                Long chooseDcId = Long.valueOf(input.readLine());
                System.out.println("창고 타입을 숫자로 입력해주세요");
                System.out.println("1. WET | 2. DRY");
                int num = Integer.parseInt(input.readLine());
                if (num == 1) {
                    warehouse.setType(WarehouseType.WET);
                } else if (num == 2) {
                    warehouse.setType(WarehouseType.DRY);
                } else {
                    System.out.println("잘못된 번호를 입력하셨습니다. 돌아갑니다");
                    return warehouse; // 유효하지 않은 입력이면 메소드 종료
                }
                pstmt.setString(1, warehouse.getType().toString());
                System.out.println("창고가 있는 지역을 입력해주세요");
                String location = input.readLine();
                pstmt.setString(2, location);
                pstmt.setLong(3,chooseDcId);
                pstmt.setLong(4, id);

                int rows = pstmt.executeUpdate();
                if (rows == 1) {
                    System.out.println("변경이 완료되었습니다");
                }
                warehouse.setLocation(location);
                pstmt.close();
                close(conn);
            }
        } catch (SQLException | IOException | NumberFormatException e) {
            e.printStackTrace(); // 실제 상황에 따라 로깅 등으로 변경
        }
        return warehouse;
    }


}
