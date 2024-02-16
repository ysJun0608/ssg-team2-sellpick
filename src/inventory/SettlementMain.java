package inventory;

import inventory.service.SettlementService;
import inventory.service.WarehouseInsertReleaseService;
import inventory.enums.WhInOutType;
import inventory.service.InventoryService;
import inventory.service.impl.InventoryServiceImpl;
import inventory.service.impl.SettlementServiceImpl;
import inventory.service.impl.WarehouseInsertReleaseServiceImpl;
import product.service.ProductsService;
import product.service.impl.ProductsServiceImpl;

import java.util.Scanner;

public class SettlementMain {
    SettlementService settlementService = new SettlementServiceImpl();
    Scanner sc = new Scanner(System.in);

    public void menu() {
        System.out.println("SettlementMain");
        boolean bool = true;

        while (bool) {
            System.out.println("1. 정산 리스트 조회");
            System.out.println("2. 특정 날짜 정산 조회");

            System.out.println("q. 종료");
            System.out.print("메뉴를 선택하세요 : ");
            String menuNum = sc.nextLine();

            switch (menuNum) {
                case "1" -> settlementService.settlementList();
                case "2" -> settlementService.settlementByDate();

                case "q" -> bool = false;
                default -> System.out.println("잘못된 입력값입니다.");
            }
        }
    }
}
