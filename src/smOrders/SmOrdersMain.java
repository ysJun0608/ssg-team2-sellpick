package smOrders;

import smOrders.service.SmOrdersService;
import smOrders.service.impl.SmOrderServiceImpl;

import java.util.Scanner;

public class SmOrdersMain {
    SmOrdersService smOrdersService = new SmOrderServiceImpl();
    Scanner sc = new Scanner(System.in);
    public void menu() {
        System.out.println("SmOrdersMain");
        boolean bool = true;
        while (bool) {
            System.out.println("1. 주문 조회");
            System.out.println("2. 주문 전체 조회");
            System.out.println("3. 취소된 주문 조회");
            System.out.println("4. 배송준비중인 주문 조회");
            System.out.println("5. 주문 상태 업데이트");
            System.out.println("6. 주문 하기");
            System.out.println("q. 종료");
            System.out.print("메뉴를 선택하세요 : ");
            String menuNum = sc.nextLine();

            switch (menuNum) {
                case "1" -> smOrdersService.readOne();
                case "2" -> smOrdersService.readOrder();
                case "3" -> smOrdersService.readAllCanceledOrders();
                case "4" -> smOrdersService.readAllPreparedOrders();
                case "5" -> smOrdersService.updateStatus();
                case "6" -> smOrdersService.createOrder();

                case "q" -> bool = false;
                default -> System.out.println("잘못된 입력값입니다.");
            }
        }

    }
}
