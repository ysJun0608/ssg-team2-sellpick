package inventory.dao;

import DBIO.ObjectDBIO;
import inventory.domain.InsertReleaseType;
import inventory.domain.Warehouse;
import inventory.domain.WarehouseInsertRelease;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;

public class WarehouseInsertReleaseDao extends ObjectDBIO {
    Connection conn = null;
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    // TODO : implement
    // 입고데이터 생성하기
    public WarehouseInsertRelease warehouseInsert(Warehouse warehouse){ // 창고를 가져오기
        WarehouseInsertRelease warehouseInsertRelease = new WarehouseInsertRelease();
        try {
            open();
            String sql = new StringBuilder()
                    .append("INSERT INTO warehouse_insert_release ")
                    .append("(quantity, totalPrice, date, type, productid) ")
                    .append("VALUES (?, ?, now(), ?, ?) where ")
                    .toString();

            warehouseInsertRelease.setType(InsertReleaseType.Insert);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(4,warehouseInsertRelease.getType().toString());

            System.out.println("입고된 상품의 번호를 입력해주세요");
            long productid = Long.parseLong(input.readLine());
            pstmt.setLong(5,productid);
            System.out.println("입고된 수량을 입력해주세요");
            long quantity = Long.parseLong(input.readLine());
            pstmt.setLong(1,quantity);
            System.out.println("입고된 물품의 총 가격을 입력해주세요");
            long totalprice = Long.parseLong(input.readLine());
            pstmt.setLong(2,totalprice);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("입고기록이 생성되었습니다.");
            }
            warehouseInsertRelease.setDate(new Date());
            warehouseInsertRelease.setQuantity(quantity);
            warehouseInsertRelease.setTotalPrice(totalprice);
            warehouseInsertRelease.setProductid(productid);
            pstmt.close();
            close(conn);
        } catch (Exception e) {
        e.printStackTrace();}
    return warehouseInsertRelease;}


   // 출고데이터 생성하기
    public WarehouseInsertRelease warehouserelease(Warehouse warehouse){ // 창고를 가져오기
        WarehouseInsertRelease warehouseInsertRelease = new WarehouseInsertRelease();
        try {
            open();
            String sql = new StringBuilder()
                    .append("INSERT INTO warehouse_insert_release ")
                    .append("(quantity, totalPrice, date, type, productid) ")
                    .append("VALUES (?, ?, now(), ?, ?)")
                    .toString();
            // 창고를 파라미터로 받긴하는데 창고를 아직 사용하진 않는다.
            warehouseInsertRelease.setType(InsertReleaseType.Release);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(4,warehouseInsertRelease.getType().toString());
            System.out.println("출고된 상품의 번호를 입력해주세요");
            long productid = Long.parseLong(input.readLine());
            pstmt.setLong(5,productid);
            System.out.println("출고된 수량을 입력해주세요");
            long quantity = Long.parseLong(input.readLine());
            pstmt.setLong(1,quantity);
            System.out.println("출고된 물품의 총 가격을 입력해주세요");
            long totalprice = Long.parseLong(input.readLine());
            pstmt.setLong(2,totalprice);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("출고기록이 생성되었습니다.");
            }
            warehouseInsertRelease.setDate(new Date());
            warehouseInsertRelease.setQuantity(quantity);
            warehouseInsertRelease.setTotalPrice(totalprice);
            warehouseInsertRelease.setProductid(productid);
            pstmt.close();
            close(conn);
        } catch (Exception e) {
            e.printStackTrace();}

    return warehouseInsertRelease;}

}
