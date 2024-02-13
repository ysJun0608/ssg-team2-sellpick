package main;

import inventory.dao.WarehouseDao;
import inventory.dao.WarehouseInsertReleaseDao;

public class MainMenu {
    public static void main(String[] args) {
        WarehouseDao dao = new WarehouseDao();
        WarehouseInsertReleaseDao insertReleaseDao = new WarehouseInsertReleaseDao();
        dao.createWarehouse();
       // insertReleaseDao.readProduct();
      //  dao.readWarehouse();
    }
}
