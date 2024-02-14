package inventory.service;

import delivery.domain.DeliveryCmp;
import inventory.domain.Warehouse;

import java.util.ArrayList;

public interface WarehouseService {
    void createWarehouse();


Warehouse updateWarehouse();

Warehouse allUpdateWarehouse(Warehouse warehouse);
Warehouse deliveryUpdateWarehouse(Warehouse warehouse);

}
