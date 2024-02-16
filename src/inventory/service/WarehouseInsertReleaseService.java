package inventory.service;

import inventory.enums.WhInOutType;
import mgtOrders.domain.WarehouseInsertRelease;

import java.util.ArrayList;

public interface WarehouseInsertReleaseService {

    /**
     *
     * @return 배송완료된 메소드 부르는
     */
    ArrayList<WarehouseInsertRelease> findStatusDone();

    void updateReleaseStatus(WhInOutType whInOutType);
}
