package delivery;

import delivery.service.WaybillService;
import delivery.service.impl.WaybillServiceImpl;

import java.util.Scanner;

public class deliveryMain {
    WaybillService waybillService = new WaybillServiceImpl();
    Scanner sc = new Scanner(System.in);
    public void menu() {
        System.out.println("운송 메인 메뉴 입니다.");
        boolean bool = true;
        while (bool) {
            System.out.println("1. 운송장 전체 조회");
            System.out.println("2. 운송장 조회");
            System.out.println("q. 종료");
            System.out.println("메뉴를 선택하세요 : ");
            String menuNum = sc.nextLine();

            switch (menuNum) {
                case "1" -> waybillService.readAllWaybill();
                case "2" -> waybillService.readOneWaybill();

                case "q" -> bool = false;
                default -> System.out.println("잘못된 입력값입니다.");
            }
        }
    }
}
