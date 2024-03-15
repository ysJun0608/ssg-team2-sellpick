package com.ssg.wsmt.inventory.service;

import com.ssg.wsmt.inventory.enums.WhInOutType;
import com.ssg.wsmt.mgtOrders.domain.WarehouseInsertRelease;

import java.util.ArrayList;

public interface WarehouseInsertReleaseService {

    /**
     *
     * @return 배송완료된 메소드 부르는
     */
    ArrayList<WarehouseInsertRelease> findStatusDone();

    void updateReleaseStatus(WhInOutType whInOutType);
}
