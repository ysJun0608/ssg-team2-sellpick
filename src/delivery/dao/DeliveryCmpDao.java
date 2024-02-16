package delivery.dao;

import DBIO.ObjectDBIO;
import delivery.domain.DeliveryCmp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryCmpDao extends ObjectDBIO {

    Connection conn = null;

    /**
     * 데이터베이스에서 택배사 정보를 읽어오고, 사용자에게 선택할 수 있도록 출력한 후
     * 선택한 택배사의 ID를 반환합니다.
     *
     * @return 선택한 택배사의 ID.
     */
    public List<DeliveryCmp> readDeliveryCmp() {
        // 택배사 정보를 담을 리스트 생성
        List<DeliveryCmp> deliveryCmps = new ArrayList<>();

        try {
            conn = open();

            String selectDeliveryCmp = "SELECT ID, NAME FROM DELIVERY_CMP";
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

                // 리소스 정리
                dcRs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deliveryCmps;
    }
}
