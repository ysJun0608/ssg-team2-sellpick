package inventory.service;

import inventory.domain.Warehouse;

import java.io.IOException;

public interface WarehouseService {
    void createWarehouse();


    Warehouse updateWarehouse();

    Warehouse allUpdateWarehouse(Warehouse warehouse);

    Warehouse deliveryUpdateWarehouse(Warehouse warehouse);

    void readAllWarehouse();

    void readOneWarehouse();
}
