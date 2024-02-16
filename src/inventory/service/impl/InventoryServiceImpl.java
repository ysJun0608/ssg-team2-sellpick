package inventory.service.impl;

import inventory.dao.InventoryDao;
import inventory.domain.Inventory;
import inventory.dto.InventoryOutput;
import inventory.service.InventoryService;

import java.util.List;
import java.util.Scanner;

public class InventoryServiceImpl implements InventoryService {
    InventoryDao inventoryDao = new InventoryDao();
    static Scanner sc = new Scanner(System.in);

    @Override
    public void searchInventoryList() {
        List<Inventory> inventoryList = inventoryDao.findAll();

        if (inventoryList.isEmpty()) {
            System.out.println("재고가 존재하지 않습니다.");
            return;
        }
        for (Inventory i : inventoryList) {
            System.out.println(i);
        }
    }

    @Override
    public void searchInventory() {
        System.out.println("찾고자 하는 재고의 id를 입력해주세요 : ");
        Long id = Long.parseLong(sc.nextLine());
        Inventory inventory = inventoryDao.findById(id);

        if (inventory == null) {
            System.out.println("해당 id의 재고가 존재하지 않습니다.");
            return;
        }
        System.out.println(inventory);
    }

    @Override
    public void searchInventoryListByWarehouse() {
        System.out.println("=".repeat(50));
        System.out.println("창고별 재고 조회");
        System.out.println("=".repeat(50));
        System.out.println("찾고자 하는 창고의 id를 입력해주세요 : ");
        Long id = Long.parseLong(sc.nextLine());
        List<InventoryOutput> inventoryList = inventoryDao.findByWarehouseId(id);

        if (inventoryList.isEmpty()) {
            System.out.println("해당 창고에 재고가 존재하지 않습니다.");
            return;
        }
        for (InventoryOutput inventory : inventoryList) {
            System.out.println(inventory);
        }
    }
}
