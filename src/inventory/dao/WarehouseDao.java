package inventory.dao;

import DBIO.ObjectDBIO;
import delivery.domain.DeliveryCmp;
import inventory.domain.WarehouseType;
import inventory.domain.Warehouse;
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
    /**
     * 데이터베이스에서 택배사 정보를 읽어오고, 사용자에게 선택할 수 있도록 출력한 후
     * 선택한 택배사의 ID를 반환합니다.
     *
     * @return 선택한 택배사의 ID.
     */
    public Long readDeliveryCmp() {
        Long chooseDcId = null;

        try {
            conn = open();

            // 택배사 정보를 담을 리스트 생성
            ArrayList<DeliveryCmp> deliveryCmps = new ArrayList<>();
            String selectDeliveryCmp = "SELECT id, name FROM delivery_cmp";
            try (PreparedStatement pstmtD = conn.prepareStatement(selectDeliveryCmp)) {
                ResultSet dcRs = pstmtD.executeQuery();

                // 데이터베이스에서 가져온 택배사 정보를 리스트에 추가
                while (dcRs.next()) {
                    Long id = dcRs.getLong("id");
                    String name = dcRs.getString("name");
                    DeliveryCmp dc = new DeliveryCmp();
                    dc.setId(id);
                    dc.setName(name);
                    deliveryCmps.add(dc);
                }

                // 리스트에 있는 택배사 정보 출력
                for (DeliveryCmp deliveryCmp : deliveryCmps) {
                    System.out.println(deliveryCmp.toString());
                }

                // 사용자에게 입력 받은 택배사의 ID를 설정
                System.out.println("창고에서 사용할 택배사 번호를 입력해주세요");
                chooseDcId = Long.valueOf(input.readLine());

                // 리소스 정리
                dcRs.close();
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return chooseDcId;
    }


    /**
     * 새로운 창고를 생성하고 택배 회사와 연결을 설정합니다.
     *
     * @return 생성된 Warehouse 객체.
     * @throws IOException I/O 오류가 발생한 경우.
     * @param Location 창고가 있는 지역
     * @param warehouseType 창고 타입 (1: WET, 2: DRY)
     * @param chooseDcId 선택한 택배사의 ID
     * @throws IOException I/O 오류가 발생한 경우
     * @throws Exception 다른 예외가 발생한 경우
     * 리펙토링으로 추후에 나눠야함
     */
    public Warehouse createWarehouse() {
        Warehouse warehouse = new Warehouse();
        try {
            conn = open();
            /**
             * 택배사 데이터 가져와서 창고와 연동할 데이터 추가하는 코드
             */
            Long chooseDcId = readDeliveryCmp();
            /**
             * 창고 생성하는 기능
             * 추후 리펙토링 필요
             */
            String insertsql = "insert into warehouse(type,location,DELIVERY_CMP_ID) values(?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(insertsql, Statement.RETURN_GENERATED_KEYS);
            System.out.println("창고가 있는 지역을 입력해주세요");
            String location = input.readLine();
            System.out.println("창고 타입을 숫자로 입력해주세요");
            System.out.println("1. WET | 2. DRY");
            int num = Integer.parseInt(input.readLine());
            /**
             * warehouse type set
             */

            if (num == 1) {
                warehouse.setType(WarehouseType.WET);
            } else if (num == 2) {
                warehouse.setType(WarehouseType.DRY);
            } else {
                System.out.println("잘못된 번호를 입력하셨습니다.");
            }
            /**
             *  warehouse location set
             */
            warehouse.setLocation(location);
            /**
             * warehouse deliveryid set
             */
            warehouse.setDelivery_id(chooseDcId);
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
            /**
             * warehouse id set
             */
            warehouse.setId(warehousePk);
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
        //System.out.println(warehouse.toString()); 실험해본다고 작성한것
        /**
         * createsection에 바로 연결하려고 작성
         */
        createSection(warehouse);
        return warehouse;
    }

    /**
     * 창고에 새로운 섹션을 추가하고 데이터베이스에 반영합니다.
     * 현재는 냉동식품, 냉장식품, 가공식품, 건조식품 섹션을 추가합니다.
     *
     * @param warehouse 섹션을 추가할 창고에 대한 Warehouse 객체.
     * @return 섹션이 추가된 Warehouse 객체.
     */
    public Warehouse createSection(Warehouse warehouse) {
        try {
            conn = open();

            // SQL 쿼리문 정의
            String sql = "INSERT INTO WAREHOUSE_SECTION (WAREHOUSE_ID, SECTION_NAME) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            Long warehouseId = warehouse.getId();

            // 냉동식품 섹션 추가
            pstmt.setLong(1, warehouseId);
            pstmt.setString(2, "냉동식품");
            pstmt.executeUpdate();

            // 냉장식품 섹션 추가
            pstmt.setLong(1, warehouseId);
            pstmt.setString(2, "냉장식품");
            pstmt.executeUpdate();

            // 가공식품 섹션 추가
            pstmt.setLong(1, warehouseId);
            pstmt.setString(2, "가공식품");
            pstmt.executeUpdate();

            // 건조식품 섹션 추가
            pstmt.setLong(1, warehouseId);
            pstmt.setString(2, "건조식품");
            pstmt.executeUpdate();

            pstmt.close();
            close(conn);

        } catch (SQLException e) {
            e.printStackTrace(); // 실제 상황에 따라 로깅 등으로 변경
        }

        // 섹션이 추가된 Warehouse 객체 반환
        return warehouse;
    }


    //창고확인 메소드

    /**
     * 데이터베이스에서 모든 창고 정보를 읽어오고, 사용자에게 창고 ID를 입력받아 해당 창고를 선택한 후
     * 선택한 창고에 대한 Warehouse 객체를 반환합니다. 또한, 데이터베이스에서 가져온 각 창고의 세부 정보를 출력합니다.
     *
     * @return 선택한 창고에 대한 Warehouse 객체.
     * @return selectMenu 메소드 호출 (warehouse객체를 가지고간다)
     * 추후 리펙토링 필요
     */
    public Warehouse readWarehouse() {
        // 데이터베이스 연결을 열고 Warehouse 객체 생성
        conn = open();
        Warehouse warehouse = new Warehouse();

        try {
            // 데이터베이스에서 모든 창고 정보를 조회하는 SQL 쿼리 실행
            String selectAll = "SELECT * FROM warehouse";
            PreparedStatement pstmt = conn.prepareStatement(selectAll);
            ResultSet rs = pstmt.executeQuery();

            // 데이터베이스에서 가져온 각 행을 Warehouse 객체에 매핑하고 리스트에 추가
            while (rs.next()) {
                Warehouse rows = new Warehouse();
                rows.setId(Long.valueOf(rs.getLong("ID")));
                rows.setDelivery_id(Long.valueOf(rs.getString("DELIVERY_CMP_ID")));
                rows.setType(WarehouseType.valueOf(rs.getString("TYPE")));
                rows.setLocation(rs.getString("LOCATION"));
                System.out.println(rows.toString());
                warehouses.add(rows);
            }

            // 사용자에게 입력 받은 창고의 ID로 해당 창고 정보를 찾아 설정
            System.out.println("불러오고 싶은 창고의 ID를 입력해주세요");
            Long id = Long.parseLong(input.readLine());
            for (Warehouse w : warehouses) {
                if (w.getId() == id) {
                    warehouse.setId(w.getId());
                    warehouse.setDelivery_id(w.getDelivery_id());
                    warehouse.setType(w.getType());
                    warehouse.setLocation(w.getLocation());
                }
            }

            // 리소스 정리
            rs.close();
            pstmt.close();
            close(conn);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        // 선택한 창고의 세부 정보를 출력하고 메뉴를 선택하도록 메서드 호출
        System.out.println(warehouse.toString());
        selectMenu(warehouse);

        // 선택한 Warehouse 객체 반환
        return warehouse;
    }


    /**
     * 사용자에게 창고 세부사항을 변경할 메뉴를 제공하고, 선택된 옵션에 따라 해당 기능을 수행합니다.
     * - 옵션 1: 택배사 변경
     * - 옵션 2: 전체 정보 변경
     *
     * @param warehouse 변경할 창고에 대한 Warehouse 객체.
     * @return 변경된 Warehouse 객체.
     * 추후 리펙토링 필요
     */
    public Warehouse selectMenu(Warehouse warehouse) {
        try {
            System.out.println("변경하고자 하는 창고의 세부사항을 입력해주세요");
            System.out.println("1. 택배사 변경 | 2. 전체변경");
            String chooseNum = input.readLine();
            switch (chooseNum) {
                case "1" -> updateDeliveryCmpId(warehouse);
                case "2" -> updateWarehouse(warehouse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return warehouse;
    }


    /**
     * 창고의 택배사 ID를 업데이트하고 데이터베이스에 반영합니다.
     *
     * @param warehouse 택배사 ID를 업데이트할 Warehouse 객체.
     *                  추후 리펙토링 필요
     */
    public void updateDeliveryCmpId(Warehouse warehouse) {
        // 데이터베이스 연결을 열고 새로운 택배사 ID를 가져옴
        conn = open();
        Long chooseDcId = readDeliveryCmp();

        try {
            // Warehouse 객체에서 ID를 얻어와 해당 창고의 택배사 ID를 업데이트
            Long id = warehouse.getId();
            String sql = "UPDATE warehouse SET DELIVERY_CMP_ID = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setLong(1, chooseDcId);
            pstmt.setLong(2, id);
            int rows = pstmt.executeUpdate();

            // 업데이트가 성공하면 메시지 출력
            if (rows == 1) {
                System.out.println("변경이 완료되었습니다.");
            }

            // 리소스 정리
            pstmt.close();
            close(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 창고의 정보를 업데이트하고 데이터베이스에 반영합니다.
     * 창고 타입, 위치, 택배사 ID를 변경할 수 있습니다.
     *
     * @param warehouse 업데이트할 Warehouse 객체.
     * @return 업데이트된 Warehouse 객체.
     * 추후 리펙토링 필요
     */
    public Warehouse updateWarehouse(Warehouse warehouse) {
        try {
            conn = open();
            Long chooseDcId = readDeliveryCmp();
            String sql = "UPDATE warehouse SET type = ?, location = ?, DELIVERY_CMP_ID = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

                Long id = warehouse.getId();

                // 창고 타입 입력 및 Warehouse 객체 업데이트
                System.out.println("창고 타입을 숫자로 입력해주세요");
                System.out.println("1. WET | 2. DRY");
                int num = Integer.parseInt(input.readLine());
                if (num == 1) {
                    warehouse.setType(WarehouseType.WET);
                } else if (num == 2) {
                    warehouse.setType(WarehouseType.DRY);
                } else {
                    System.out.println("잘못된 번호를 입력하셨습니다. 돌아갑니다");
                    return warehouse; // 유효하지 않은 입력이면 메서드 종료
                }

                // PreparedStatement에 값 설정
                pstmt.setString(1, warehouse.getType().toString());
                System.out.println("창고가 있는 지역을 입력해주세요");
                String location = input.readLine();
                pstmt.setString(2, location);
                pstmt.setLong(3, chooseDcId);
                pstmt.setLong(4, id);

                // 업데이트 실행 및 결과 확인
                int rows = pstmt.executeUpdate();
                if (rows == 1) {
                    System.out.println("변경이 완료되었습니다");
                }

                // Warehouse 객체에 업데이트된 정보 설정
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
