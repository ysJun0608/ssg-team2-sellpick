package com.ssg.wsmt.inventory.service.impl;

import com.ssg.wsmt.inventory.dao.InventoryDao;
import com.ssg.wsmt.inventory.domain.Inventory;
import com.ssg.wsmt.inventory.dto.InventoryOutput;
import com.ssg.wsmt.inventory.mapper.InventoryMapper;
import com.ssg.wsmt.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;
@RequiredArgsConstructor
@Service
public class InventoryServiceImpl implements InventoryService {
    private final InventoryMapper inventoryMapper;
//   InventoryDao inventoryDao = new InventoryDao();
    static Scanner sc = new Scanner(System.in);

    @Override
    public void searchInventoryList() {
        List<Inventory> inventoryList = inventoryMapper.findAll();

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
        Inventory inventory = inventoryMapper.findById(id);

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
        List<InventoryOutput> inventoryList = inventoryMapper.findByWarehouseId(id);

        if (inventoryList.isEmpty()) {
            System.out.println("해당 창고에 재고가 존재하지 않습니다.");
            return;
        }
        for (InventoryOutput inventory : inventoryList) {
            System.out.println(inventory);
        }
    }
}
