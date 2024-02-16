package inventory.service.impl;

import delivery.service.DeliveryCmpService;
import delivery.service.impl.DeliveryCmpServiceImpl;
import inventory.dao.WarehouseDao;
import inventory.domain.Warehouse;
import inventory.domain.WhSmRelationship;
import inventory.enums.WhType;
import inventory.service.WarehouseSectionService;
import inventory.service.WarehouseService;
import inventory.service.WhSmRelationshipService;
import smOrders.service.ShoppingMallService;
import smOrders.service.impl.ShoppingMallServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class WarehouseServiceImpl implements WarehouseService {
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    WarehouseDao warehouseDao = new WarehouseDao();
    DeliveryCmpService deliveryCmpService = new DeliveryCmpServiceImpl();
    ShoppingMallService shoppingMallService = new ShoppingMallServiceImpl();
    WhSmRelationshipService whSmRelationShipService = new WhSmRelationshipServiceImpl();
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
            System.out.println("=".repeat(50));

            System.out.println("창고 타입을 숫자로 입력해주세요");
            System.out.println("1. WET | 2. DRY | 3.BOTH ");
            int num = Integer.parseInt(input.readLine());
            System.out.println("=".repeat(50));
            if (num == 1) {
                warehouse.setType(WhType.WET);
            } else if (num == 2) {
                warehouse.setType(WhType.DRY);
            } else if (num == 3) {
                warehouse.setType(WhType.BOTH);
            } else {
                System.out.println("잘못된 번호를 입력하셨습니다.");
            }
            System.out.println("=".repeat(50));
        } catch (IOException e) {
            e.printStackTrace();
        }

        warehouse.setDelivery_id(chooseDcId);

        // 창고 생성
        Long warehousePk = warehouseDao.createWarehouse(warehouse);

        // 창고 객체에 PK 설정
        warehouse.setId(warehousePk);

        // 창고와 쇼핑몰 연동
        Long smNum = shoppingMallService.chooseShoppingMall();
        // 창고와 쇼핑몰 연동 객체 생성
        WhSmRelationship whSmRelationShip = new WhSmRelationship();
        whSmRelationShip.setWarehouseId(warehousePk);
        whSmRelationShip.setShoppingMallId(smNum);

        // 창고와 쇼핑몰 연동 저장
        whSmRelationShipService.createWhSmRelationship(whSmRelationShip);

        // 창고 구역 생성
        warehouseSectionService.createWarehouseSection(warehouse);
    }


    @Override
    public Warehouse updateWarehouse() {
        List<Warehouse> warehouseList = warehouseDao.readWarehouse();
        Warehouse warehouse = new Warehouse();

        try {
            System.out.println("=".repeat(50));
            System.out.printf("%-1s | %-4s | %-4s | %-4s\n", "창고번호", "택배사번호", "창고타입", "지역");
            System.out.println("=".repeat(50));
            for (Warehouse w : warehouseList) {
                System.out.printf("%-7d | %-7d | %-6s | %-4s\n", w.getId(), w.getDelivery_id(), w.getType(), w.getLocation());
            }
            System.out.println("=".repeat(50));
            System.out.println("택배사를 변경하고싶은 창고의 ID를 입력해주세요");
            Long id = Long.parseLong(input.readLine());
            for (Warehouse w : warehouseList) {
                if (id == w.getId()) {
                    warehouse.setLocation(w.getLocation());
                    warehouse.setType(w.getType());
                    warehouse.setId(w.getId());
                }
            }
            deliveryUpdateWarehouse(warehouse);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return warehouse;
    }

    @Override
    public Warehouse deliveryUpdateWarehouse(Warehouse warehouse) {
        Long chooseDcId = deliveryCmpService.chooseDeliveryCmp();
        warehouse.setDelivery_id(chooseDcId);
        warehouseDao.updateDeliveryCmpId(warehouse);
        return warehouse;
    }

    @Override
    public void readAllWarehouse() {
        List<Warehouse> warehouseList = warehouseDao.readWarehouse();
        if (warehouseList.isEmpty()) {
            System.out.println("창고가 존재하지 않습니다.");
            return;
        }
        System.out.println("=".repeat(50));
        System.out.printf("%-1s | %-4s | %-4s | %-4s\n", "창고번호", "택배사번호", "창고타입", "지역");
        System.out.println("=".repeat(50));
        for (Warehouse w : warehouseList) {
            System.out.printf("%-7d | %-7d | %-6s | %-4s\n", w.getId(), w.getDelivery_id(), w.getType(), w.getLocation());
        }
        System.out.println("=".repeat(50));
    }

    @Override
    public void readOneWarehouse() {
        Long id = 0l;

        try {
            System.out.println("창고의 ID를 입력해주세요");
            id = Long.parseLong(input.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Warehouse warehouse = warehouseDao.findById(id);
        if (warehouse == null) {
            System.out.println("없는 창고입니다.");
            return;
        }

        System.out.println("=".repeat(50));
        System.out.printf("%-1s | %-4s | %-4s | %-4s\n", "창고번호", "택배사번호", "창고타입", "지역");
        System.out.println("=".repeat(50));
        System.out.printf("%-7d | %-7d | %-6s | %-4s\n", warehouse.getId(), warehouse.getDelivery_id(), warehouse.getType(), warehouse.getLocation());
        System.out.println("=".repeat(50));
    }

    @Override
    public Warehouse allUpdateWarehouse(Warehouse warehouse) {
        Long chooseDcId = deliveryCmpService.chooseDeliveryCmp();
        warehouse.setDelivery_id(chooseDcId);
        warehouseDao.updateDeliveryCmpId(warehouse);

        return warehouse;
    }
}
