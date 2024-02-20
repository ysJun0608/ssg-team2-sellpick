package main;

import config.printWMSTraing.WmsTraining;

import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
        new WmsTraining().logo();
        System.out.println("=".repeat(50));
        System.out.println("Welcome to 2커머스팀의 WMST 프로젝트");
        System.out.println("=".repeat(50));
        while (true) {
            selectMenu();
        }
    }

    static Scanner scanner = new Scanner(System.in);

    private static void selectMenu() {
        System.out.println("1. 창고관리");
        System.out.println("2. 재고관리");
        System.out.println("3. 발주관리");
        System.out.println("4. 주문관리");
        System.out.println("5. 운송관리");
        System.out.println("6. 정산관리");
        System.out.println("q. 종료");
        System.out.print("메뉴를 선택하세요 : ");
        String menuNum = scanner.nextLine();

        switch (menuNum) {
            case "1" -> new inventory.WarehouseMain().menu();
            case "2" -> new inventory.InventoryMain().menu();
            case "3" -> new mgtOrders.MgtOrdersMain().menu();
            case "4" -> new smOrders.SmOrdersMain().menu();
            case "5" -> new delivery.deliveryMain().menu();
            case "6" -> new inventory.SettlementMain().menu();

            case "q" -> {
                new config.printEndImage.ImageDisplay().printImage();
                try {
                    Thread.sleep(7500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
            default -> System.out.println("잘못된 입력값입니다.");
        }
    }
}
