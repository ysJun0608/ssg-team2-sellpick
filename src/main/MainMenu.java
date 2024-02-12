package main;

import inventory.dao.WarehouseDao;

public class MainMenu {
    public static void main(String[] args) {
        WarehouseDao dao = new WarehouseDao();

        dao.readWarehouse();
    }
}
