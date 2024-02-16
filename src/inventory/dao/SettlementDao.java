package inventory.dao;

import DBIO.ObjectDBIO;
import inventory.domain.Settlement;
import inventory.domain.WarehouseInsertRelease;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SettlementDao extends ObjectDBIO {
    Connection conn = null;
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public Settlement SettlementCreate(WarehouseInsertRelease warehouseInsertRelease) {
        // 입출고 테이블에서 가져와야됌
        Settlement settlement = new Settlement();
        try {
            conn = open();
            String sql = "INSERT INTO SETTLEMENT (IN_QUANTITY,OUT_QUANTITY,TOTAL_PRICE,CREATED_AT,MODIFIED_AT) " +
                    "VALUES (?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            System.out.println("정산하실 날짜를 입력하세요"); // 입력 형식이 어떻게 되는지 체크해야됌

            System.out.println("입고된 총 수량을 입력하세요");
            System.out.println("출고된 총 수량을 입력하세요");
            // 이 사이에서 입 출고된 물품들의 수량과 금액을 가지고 연산을 해야됌
            System.out.println("금일의 총 정산금액을 말씀드리겠습니다.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return settlement;
    }

    public Optional<List<Settlement>> settlementList() {
        List<Settlement> settlementList = new ArrayList<>();
        try {
            conn = open();
            String sql = "SELECT * FROM SETTLEMENT";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                Settlement settlement = new Settlement();
                settlement.setId(rs.getLong("id"));
                settlement.setInsertQuantity(rs.getLong("in_quantity"));
                settlement.setReleaseQuantity(rs.getLong("out_quantity"));
                settlement.setTotalPrice(rs.getLong("total_price"));
                settlement.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                settlement.setModifiedAt(rs.getTimestamp("modified_at").toLocalDateTime());
                settlementList.add(settlement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.of(settlementList);
    }

    public Optional<Settlement> settlementByDate(String date) {
        Settlement settlement = new Settlement();
        try {
            conn = open();
            // 입력받은 날짜(년, 월, 일) 같은 지 체크
            String sql = "SELECT * FROM SETTLEMENT " +
                    "WHERE CREATED_AT = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, date);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                settlement.setId(rs.getLong("id"));
                settlement.setInsertQuantity(rs.getLong("in_quantity"));
                settlement.setReleaseQuantity(rs.getLong("out_quantity"));
                settlement.setTotalPrice(rs.getLong("total_price"));
                settlement.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                settlement.setModifiedAt(rs.getTimestamp("modified_at").toLocalDateTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.of(settlement);
    }
}
