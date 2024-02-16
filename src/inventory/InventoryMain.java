package inventory;

import inventory.service.WarehouseInsertReleaseService;
import inventory.enums.WhInOutType;
import inventory.service.InventoryService;
import inventory.service.impl.InventoryServiceImpl;
import inventory.service.impl.WarehouseInsertReleaseServiceImpl;
import product.service.ProductsService;
import product.service.impl.ProductsServiceImpl;

import java.util.Scanner;

public class InventoryMain {
    InventoryService inventoryService = new InventoryServiceImpl();
    WarehouseInsertReleaseService warehouseInsertReleaseService = new WarehouseInsertReleaseServiceImpl();
    ProductsService productsService = new ProductsServiceImpl();
    Scanner sc = new Scanner(System.in);

    public void menu() {
        System.out.println("InventoryMain");
        boolean bool = true;

        while (bool) {
            System.out.println("1. 재고 전체 조회");
            System.out.println("2. 재고 조회");
            System.out.println("3. 출고 확정");
            System.out.println("4. 출고 취소");
            System.out.println("5. 창고별 재고 조회");
            System.out.println("6. 상품 추가");
            System.out.println("7. 상품 수정");
//            System.out.println("8. 상품 삭제");

            System.out.println("q. 종료");
            System.out.print("메뉴를 선택하세요 : ");
            String menuNum = sc.nextLine();

            switch (menuNum) {
                case "1" -> inventoryService.searchInventoryList();
                case "2" -> inventoryService.searchInventory();
                case "3" -> warehouseInsertReleaseService.updateReleaseStatus(WhInOutType.RELEASE_CONFIRM);
                case "4" -> warehouseInsertReleaseService.updateReleaseStatus(WhInOutType.RELEASE_CANCEL);
                case "5" -> inventoryService.searchInventoryListByWarehouse();
                case "6" -> productsService.createProduct();
                case "7" -> productsService.updateProduct();

                case "q" -> bool = false;
                default -> System.out.println("잘못된 입력값입니다.");
            }
        }
    }
}
