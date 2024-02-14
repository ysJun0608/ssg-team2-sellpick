package inventory.service;

import mgtOrders.domain.MgtOrderProductsRelationship;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface WarehouseInsertReleaseService {

    /**
     *
     * @return 배송완료된 메소드 부르는
     */
    ArrayList<MgtOrderProductsRelationship> findStatusDone();
}
