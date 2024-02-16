package mgtOrders;

import mgtOrders.service.MgtOrdersService;
import mgtOrders.service.impl.MgtOrdersServiceImpl;

import java.util.Scanner;

public class MgtOrdersMain {
    MgtOrdersService mgtOrdersService = new MgtOrdersServiceImpl();
    Scanner sc = new Scanner(System.in);


    public void menu() {
        System.out.println("MgtOrdersMain menu");
        boolean bool = true;
        while (bool) {
            //    void add();
            //    void delete();
            //    void getAllOrders();
            //    boolean confirmOrder();
            //    boolean cancelOrder();
            //    void confirmList();
            //    void searchNonDelivered();
            System.out.println("1. 발주 요청");
            System.out.println("2. 발주 삭제");
            System.out.println("3. 발주 전체 조회");
            System.out.println("4. 발주 확정");
            System.out.println("5. 발주 취소");
            System.out.println("6. 발주 확정 목록");
            System.out.println("7. 요청된 발주 조회");
            System.out.println("8. 발주한 상품이 도착하여 입고처리");
            System.out.println("q. 종료");
            System.out.print("메뉴를 선택하세요 : ");
            String menuNum = sc.nextLine();

            switch (menuNum) {
                case "1" -> mgtOrdersService.add();
                case "2" -> mgtOrdersService.delete();
                case "3" -> mgtOrdersService.getAllOrders();
                case "4" -> mgtOrdersService.confirmOrder();
                case "5" -> mgtOrdersService.cancelOrder();
                case "6" -> mgtOrdersService.confirmList();
                case "7" -> mgtOrdersService.searchNonDelivered();
                case "8" -> mgtOrdersService.confirmArrival();

                case "q" -> bool = false;
                default -> System.out.println("잘못된 입력값입니다.");
            }
        }
    }
}
