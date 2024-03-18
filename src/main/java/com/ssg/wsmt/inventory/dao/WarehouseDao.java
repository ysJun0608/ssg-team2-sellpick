//package com.ssg.wsmt.inventory.dao;
//
//import com.ssg.wsmt.DBIO.ObjectDBIO;
//import com.ssg.wsmt.inventory.domain.WarehouseVO;
//import com.ssg.wsmt.inventory.enums.WhType;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class WarehouseDao extends ObjectDBIO {
//
//    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
//    Connection conn = null;
//    //ArrayList<Warehouse> warehouses = new ArrayList<>();
//
//    // TODO : implement
//    /**
//     * 데이터베이스에서 택배사 정보를 읽어오고, 사용자에게 선택할 수 있도록 출력한 후
//     * 선택한 택배사의 ID를 반환합니다.
//     *
//     * @return 선택한 택배사의 ID.
//     */
//
//
//
//
////    public Long createWarehouse(WarehouseVO warehouseVO) {
////        Long warehousePk = null;
////        try {
////            conn = open();
////            /**
////             * 창고 생성하는 기능
////             * 추후 리펙토링 필요
////             */
////            String insertsql = "INSERT INTO WAREHOUSE(TYPE,LOCATION,DELIVERY_CMP_ID) VALUES(?,?,?)";
////            PreparedStatement pstmt = conn.prepareStatement(insertsql, Statement.RETURN_GENERATED_KEYS);
////
////            pstmt.setString(1, warehouseVO.getType().toString());
////            pstmt.setString(2, warehouseVO.getLocation());
////            pstmt.setLong(3, warehouseVO.getDelivery_id());
////
////            int rows = pstmt.executeUpdate();
////            /**
////             *  warehouse pk 가져오기
////             */
////            if (rows == 1) {
////                ResultSet rs = pstmt.getGeneratedKeys();
////                while (rs.next()) {
////                    warehousePk = rs.getLong(1);
////                }
////            }
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////            return warehousePk;
////    }
//
//    /**
//     * 창고에 새로운 섹션을 추가하고 데이터베이스에 반영합니다.
//     * 현재는 냉동식품, 냉장식품, 가공식품, 건조식품 섹션을 추가합니다.
//     *
//     * @param warehouseVO 섹션을 추가할 창고에 대한 Warehouse 객체.
//     * @return 섹션이 추가된 Warehouse 객체.
//     */
//    public WarehouseVO createSection(WarehouseVO warehouseVO) {
//        try {
//            conn = open();
//
//            // SQL 쿼리문 정의
//            String sql = "INSERT INTO WAREHOUSE_SECTION (WAREHOUSE_ID, NAME) VALUES (?, ?)";
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//
//            Long warehouseId = warehouseVO.getId();
//
//            // 냉동식품 섹션 추가
//            pstmt.setLong(1, warehouseId);
//            pstmt.setString(2, "냉동식품");
//            pstmt.executeUpdate();
//
//            // 냉장식품 섹션 추가
//            pstmt.setLong(1, warehouseId);
//            pstmt.setString(2, "냉장식품");
//            pstmt.executeUpdate();
//
//            // 가공식품 섹션 추가
//            pstmt.setLong(1, warehouseId);
//            pstmt.setString(2, "가공식품");
//            pstmt.executeUpdate();
//
//            // 건조식품 섹션 추가
//            pstmt.setLong(1, warehouseId);
//            pstmt.setString(2, "건조식품");
//            pstmt.executeUpdate();
//
//            pstmt.close();
//            close(conn);
//
//        } catch (SQLException e) {
//            e.printStackTrace(); // 실제 상황에 따라 로깅 등으로 변경
//        }
//
//        // 섹션이 추가된 Warehouse 객체 반환
//        return warehouseVO;
//    }
//
//
//    //창고확인 메소드
//
//    /**
//     * 데이터베이스에서 모든 창고 정보를 읽어오고, 사용자에게 창고 ID를 입력받아 해당 창고를 선택한 후
//     * 선택한 창고에 대한 Warehouse 객체를 반환합니다. 또한, 데이터베이스에서 가져온 각 창고의 세부 정보를 출력합니다.
//     *
//     * @return 선택한 창고에 대한 Warehouse 객체.
//     * @return selectMenu 메소드 호출 (warehouse객체를 가지고간다)
//     * 추후 리펙토링 필요
//     */
//    public List<WarehouseVO> readWarehouse() {
//        // 데이터베이스 연결을 열고 Warehouse 객체 생성
//        List<WarehouseVO> warehouseVOList = new ArrayList<>();
//        conn = open();
//
//        try {
//            // 데이터베이스에서 모든 창고 정보를 조회하는 SQL 쿼리 실행
//            String selectAll = "SELECT * FROM WAREHOUSE";
//            PreparedStatement pstmt = conn.prepareStatement(selectAll);
//            ResultSet rs = pstmt.executeQuery();
//
//            // 데이터베이스에서 가져온 각 행을 Warehouse 객체에 매핑하고 리스트에 추가
//            while (rs.next()) {
//                WarehouseVO rows = new WarehouseVO();
//                rows.setId(Long.valueOf(rs.getLong("ID")));
//                rows.setDelivery_id(Long.valueOf(rs.getString("DELIVERY_CMP_ID")));
//                rows.setType(WhType.valueOf(rs.getString("TYPE")));
//                rows.setLocation(rs.getString("LOCATION"));
//                warehouseVOList.add(rows);
//            }
//
//            // 리소스 정리
//            rs.close();
//            pstmt.close();
//            close(conn);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return warehouseVOList;
//    }
//
//
//    /**
//     * 사용자에게 창고 세부사항을 변경할 메뉴를 제공하고, 선택된 옵션에 따라 해당 기능을 수행합니다.
//     * - 옵션 1: 택배사 변경
//     * - 옵션 2: 전체 정보 변경
//     *
//     * @param warehouseVO 변경할 창고에 대한 Warehouse 객체.
//     * @return 변경된 Warehouse 객체.
//     * 추후 리펙토링 필요
//     */
//    public WarehouseVO selectMenu(WarehouseVO warehouseVO) {
//        try {
//            System.out.println("변경하고자 하는 창고의 세부사항을 입력해주세요");
//            System.out.println("1. 택배사 변경 | 2. 전체변경");
//            String chooseNum = input.readLine();
//            switch (chooseNum) {
//                case "1" -> updateDeliveryCmpId(warehouseVO);
//                case "2" -> updateWarehouse(warehouseVO);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return warehouseVO;
//    }
//
//
//    /**
//     * 창고의 택배사 ID를 업데이트하고 데이터베이스에 반영합니다.
//     *
//     * @param warehouseVO 택배사 ID를 업데이트할 Warehouse 객체.
//     *                  추후 리펙토링 필요
//     */
//    public void updateDeliveryCmpId(WarehouseVO warehouseVO) {
//        // 데이터베이스 연결을 열고 새로운 택배사 ID를 가져옴
//        conn = open();
//
//
//        try {
//            // Warehouse 객체에서 ID를 얻어와 해당 창고의 택배사 ID를 업데이트
//
//            String sql = "UPDATE WAREHOUSE SET DELIVERY_CMP_ID = ? WHERE ID = ?";
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            Long id = warehouseVO.getId();
//            Long chooseDcId = warehouseVO.getDelivery_id();
//            pstmt.setLong(1, chooseDcId);
//            pstmt.setLong(2, id);
//            int rows = pstmt.executeUpdate();
//
//            // 업데이트가 성공하면 메시지 출력
//            if (rows == 1) {
//                System.out.println("변경이 완료되었습니다.");
//            }
//
//            // 리소스 정리
//            pstmt.close();
//            close(conn);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    /**
//     * 창고의 정보를 업데이트하고 데이터베이스에 반영합니다.
//     * 창고 타입, 위치, 택배사 ID를 변경할 수 있습니다.
//     *
//     * @param warehouseVO 업데이트할 Warehouse 객체.
//     * @return 업데이트된 Warehouse 객체.
//     * 추후 리펙토링 필요
//     */
//    public WarehouseVO updateWarehouse(WarehouseVO warehouseVO) {
//        try {
//            conn = open();
////
//            String sql = "UPDATE WAREHOUSE SET TYPE = ?, LOCATION = ?, DELIVERY_CMP_ID = ? WHERE ID = ?";
//            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//                Long id = warehouseVO.getId();
//
//                // 창고 타입 입력 및 Warehouse 객체 업데이트
//                System.out.println("창고 타입을 숫자로 입력해주세요");
//                System.out.println("1. WET | 2. DRY");
//                int num = Integer.parseInt(input.readLine());
//                if (num == 1) {
//                    warehouseVO.setType(WhType.WET);
//                } else if (num == 2) {
//                    warehouseVO.setType(WhType.DRY);
//                } else {
//                    System.out.println("잘못된 번호를 입력하셨습니다. 돌아갑니다");
//                    return warehouseVO; // 유효하지 않은 입력이면 메서드 종료
//                }
//
//                // PreparedStatement에 값 설정
//                pstmt.setString(1, warehouseVO.getType().toString());
//                System.out.println("창고가 있는 지역을 입력해주세요");
//                String location = input.readLine();
//                pstmt.setString(2, location);
////                pstmt.setLong(3, chooseDcId);
//                pstmt.setLong(4, id);
//
//                // 업데이트 실행 및 결과 확인
//                int rows = pstmt.executeUpdate();
//                if (rows == 1) {
//                    System.out.println("변경이 완료되었습니다");
//                }
//
//                // Warehouse 객체에 업데이트된 정보 설정
//                warehouseVO.setLocation(location);
//                pstmt.close();
//                close(conn);
//            }
//        } catch (SQLException | IOException | NumberFormatException e) {
//            e.printStackTrace(); // 실제 상황에 따라 로깅 등으로 변경
//        }
//        return warehouseVO;
//    }
//
//
//    public WarehouseVO findById(Long id) {
//        conn = open();
//        WarehouseVO warehouseVO = null;
//        try {
//            String SQL = "SELECT * FROM WAREHOUSE WHERE ID = ?";
//            PreparedStatement pstmt = conn.prepareStatement(SQL);
//
//            pstmt.setLong(1, id);
//
//            ResultSet rs = pstmt.executeQuery();
//
//            if (rs.next()) {
//                warehouseVO = new WarehouseVO();
//                warehouseVO.setId(id);
//                warehouseVO.setType(WhType.valueOf(rs.getString("TYPE")));
//                warehouseVO.setLocation(rs.getString("LOCATION"));
//                warehouseVO.setDelivery_id(rs.getLong("DELIVERY_CMP_ID"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            return warehouseVO;
//        }
//
//    }
//}