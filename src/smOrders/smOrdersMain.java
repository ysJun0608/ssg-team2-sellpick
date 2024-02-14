package smOrders;

import smOrders.service.impl.SmOrderServiceImpl;
import smOrders.service.SmOrdersService;

import java.util.Scanner;

public class smOrdersMain {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        MainMenu(); // 메뉴 메서드 호출
    }

    public static void MainMenu() {
        SmOrdersService smOrdersService = new SmOrderServiceImpl(); // SMOrderServiceImpl 객체 생성

        System.out.println("====================================================");
        System.out.println("메인메뉴: 1.Create  |  2.Read |  3.ReadAll(취소) |  4.ReadAll(배송준비중) |  5.updateStatus |  6.Exit   ");
        System.out.print("메뉴선택: ");
        String MainNum = sc.nextLine(); //메뉴 선택 입력 값
        switch (MainNum) { //MainNum ""
            case "1" -> smOrdersService.createOrder();
            case "2" -> smOrdersService.readOrder();
            case "3" -> smOrdersService.readAllCanceledOrders();
            case "4" -> smOrdersService.readAllPreparedOrders();
            case "5" -> smOrdersService.updateStatus();
//            case "5" -> smOrdersService.clearMenu();
            case "6" -> System.exit(0);
            default -> MainMenu(); // 잘못된 입력에 대해 다시 메뉴 호출
        }
    }
}
