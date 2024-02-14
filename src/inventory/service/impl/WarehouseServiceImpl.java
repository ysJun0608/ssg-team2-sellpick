package inventory.service.impl;

import delivery.service.DeliveryCmpService;
import delivery.service.impl.DeliveryCmpServiceImpl;
import inventory.dao.WarehouseDao;
import inventory.domain.Warehouse;
import inventory.domain.WhSmRelationShip;
import inventory.enums.WhType;
import inventory.service.WarehouseSectionService;
import inventory.service.WarehouseService;
import inventory.service.WhSmRelationShipService;
import smOrders.domain.ShoppingMall;
import smOrders.service.ShoppingMallService;
import smOrders.service.impl.ShoppingMallServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WarehouseServiceImpl implements WarehouseService {
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    WarehouseDao warehouseDao = new WarehouseDao();
    DeliveryCmpService deliveryCmpService = new DeliveryCmpServiceImpl();
    ShoppingMallService shoppingMallService = new ShoppingMallServiceImpl();
    WhSmRelationShipService whSmRelationShipService = new WhSmRelationShipServiceImpl();
    WarehouseSectionService warehouseSectionService = new WarehouseSectionServiceImpl();

    @Override
    public void createWarehouse() {

        // 택배사 선택
        Long chooseDcId = deliveryCmpService.chooseDeliveryCmp();
        Warehouse warehouse = new Warehouse();

        try {
            System.out.println("창고가 있는 지역을 입력해주세요");
            String location = input.readLine();
            warehouse.setLocation(location);

            System.out.println("창고 타입을 숫자로 입력해주세요");
            System.out.println("1. WET | 2. DRY | 3.BOTH ");
            int num = Integer.parseInt(input.readLine());
            if (num == 1) {
                warehouse.setType(WhType.WET);
            } else if (num == 2) {
                warehouse.setType(WhType.DRY);
            } else if (num==3) {
                warehouse.setType(WhType.BOTH);
            } else {
                System.out.println("잘못된 번호를 입력하셨습니다.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        warehouse.setDelivery_id(chooseDcId);

        Long warehousePk = warehouseDao.createWarehouse(warehouse);
        /**
         * warehouse id set
         */
        warehouse.setId(warehousePk);

        Long smNum = shoppingMallService.chooseShoppingMall();

        WhSmRelationShip whSmRelationShip = new WhSmRelationShip();
        whSmRelationShip.setWarehouseId(warehousePk);
        whSmRelationShip.setShoppingMallId(chooseDcId);

        whSmRelationShipService.createWhSmRelationShip(whSmRelationShip);


        warehouseSectionService.createWarehouseSection(warehouse);

    }



    @Override
    public Warehouse updateWarehouse() {
        ArrayList<Warehouse> warehouses = warehouseDao.readWarehouse();
        Warehouse warehouse = new Warehouse();

        try {
            System.out.println("=".repeat(50));
            System.out.printf("%-1s | %-4s | %-4s | %-4s\n", "창고번호", "택배사번호", "창고타입", "지역");
            System.out.println("=".repeat(50));
            for (Warehouse w : warehouses) {
                System.out.printf("%-7d | %-7d | %-6s | %-4s\n", w.getId(), w.getDelivery_id(), w.getType(), w.getLocation());
            }
            System.out.println("=".repeat(50));
            System.out.println("변경하고싶은 창고의 ID를 입력해주세요");
            Long id = Long.parseLong(input.readLine());
            for (Warehouse w : warehouses) {
                if (id == w.getId()) {
                    warehouse.setLocation(w.getLocation());
                    warehouse.setType(w.getType());
                    warehouse.setId(w.getId());
                }
            }

            System.out.println("변경하고자 하는 창고의 세부사항을 입력해주세요");
            System.out.println("1. 택배사 변경 | 2. 전체변경");
            String menuNumber = input.readLine();
            switch (menuNumber) {
                case "1" -> deliveryUpdateWarehouse(warehouse);
                case "2" -> allUpdateWarehouse(warehouse);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return warehouse;
    }@Override
    public Warehouse deliveryUpdateWarehouse(Warehouse warehouse) {
        Long chooseDcId = deliveryCmpService.chooseDeliveryCmp();
        warehouse.setDelivery_id(chooseDcId);
        warehouseDao.updateDeliveryCmpId(warehouse);
        return warehouse;
    }


    @Override
    public Warehouse allUpdateWarehouse(Warehouse warehouse) {
        Long chooseDcId = deliveryCmpService.chooseDeliveryCmp();
        warehouse.setDelivery_id(chooseDcId);
        warehouseDao.updateDeliveryCmpId(warehouse);


        return warehouse ;
    }



}
// TODO: implement service
