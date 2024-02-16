package inventory;

import inventory.service.WarehouseSectionService;
import inventory.service.WarehouseService;
import inventory.service.impl.WarehouseSectionServiceImpl;
import inventory.service.impl.WarehouseServiceImpl;

import java.io.IOException;
import java.util.Scanner;

public class WarehouseMain {
    WarehouseService warehouseService = new WarehouseServiceImpl();
    WarehouseSectionService warehouseSectionService = new WarehouseSectionServiceImpl();
    Scanner sc = new Scanner(System.in);
    public void menu() {
        System.out.println("WarehouseMain");
        boolean bool = true;
        while (bool) {
            System.out.println("1. 창고 생성");
            System.out.println("2. 창고 조회");
            System.out.println("3. 창고 (택배사) 수정");
            System.out.println("4. 창고 전체 조회");
            System.out.println("5. 창고 구역 검색");

            System.out.println("q. 종료");
            System.out.print("메뉴를 선택하세요 : ");
            String menuNum = sc.nextLine();

            switch (menuNum) {
                case "1" -> warehouseService.createWarehouse();
                case "2" -> warehouseService.readOneWarehouse();
                case "3" -> warehouseService.updateWarehouse();
                case "4" -> warehouseService.readAllWarehouse();
                case "5" -> warehouseSectionService.readWarehouseSection();

                case "q" -> bool = false;
                default -> System.out.println("잘못된 입력값입니다.");
            }
        }
    }
}
