package main;

import inventory.dao.WarehouseDao;
import inventory.dao.WarehouseInsertReleaseDao;
import inventory.service.WarehouseService;
import inventory.service.impl.WarehouseInsertReleaseServiceImpl;
import inventory.service.impl.WarehouseServiceImpl;
import mgtOrders.domain.MgtOrderProductsRelationship;

import java.util.ArrayList;

public class MainMenu {
    public static void main(String[] args) {
        WarehouseServiceImpl warehouseService = new WarehouseServiceImpl();
        WarehouseInsertReleaseDao insertReleaseDao = new WarehouseInsertReleaseDao();
        //  dao.createWarehouse();
        //  dao.createWarehouse();
        //  dao.createWarehouse();
        //  insertReleaseDao.readProduct();
        //  dao.readWarehouse();
        //  insertReleaseDao.findAllInsertProducts();
        // WarehouseInsertReleaseServiceImpl warehouseInsertReleaseService = new WarehouseInsertReleaseServiceImpl();
        // ArrayList<MgtOrderProductsRelationship> statusDone = warehouseInsertReleaseService.findStatusDone();
        //insertReleaseDao.updateInsertStatus(statusDone);
      // warehouseService.createWarehouse();
        warehouseService.updateWarehouse();
    }
}
