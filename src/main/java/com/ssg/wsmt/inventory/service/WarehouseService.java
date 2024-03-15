package com.ssg.wsmt.inventory.service;

import com.ssg.wsmt.inventory.domain.Warehouse;

public interface WarehouseService {
    void createWarehouse();


    Warehouse updateWarehouse();

    Warehouse allUpdateWarehouse(Warehouse warehouse);

    Warehouse deliveryUpdateWarehouse(Warehouse warehouse);

    void readAllWarehouse();

    void readOneWarehouse();
}
