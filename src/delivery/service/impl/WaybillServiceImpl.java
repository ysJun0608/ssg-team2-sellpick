package delivery.service.impl;


import delivery.dao.WaybillDao;
import delivery.dto.SelectListWaybillOutput;
import delivery.dto.SelectWaybillOutPut;
import delivery.service.WaybillService;

import java.util.List;
import java.util.Scanner;

public class WaybillServiceImpl implements WaybillService {
    Scanner sc = new Scanner(System.in);
    WaybillDao waybillDao = new WaybillDao();

    @Override
    public void readOneWaybill() {
        System.out.print("조회하고 싶은 운송장의 주문 번호를 입력해주세요: ");
        Long ordersNum = Long.parseLong(sc.nextLine());

        List<SelectWaybillOutPut> outputList = waybillDao.findByOrderId(ordersNum);

        for (SelectWaybillOutPut output : outputList) {
            System.out.println("-".repeat(50));
            System.out.println("운송장 번호: " + output.id());
            System.out.println("주문 번호: " + output.ordersId());
            System.out.println("상품 이름: " + output.productName());
            System.out.println("-- 보낸 이 --");
            System.out.println("쇼핑몰 이름: " + output.shoppingMallName());
            System.out.println("창고 주소: " + output.warehouseLocation());
            System.out.println("사업자 휴대폰 번호: " + output.businessPhone());
            System.out.println("-- 받는 이 --");
            System.out.println("고객 ID: " + output.customerId());
            System.out.println("고객 이름: " + output.customerName());
            System.out.println("고객 핸드폰번호: " + output.customerPhone());
            System.out.println("고객 주소: " + output.customerAddress());
            System.out.println("-".repeat(50));
        }
    }

    @Override
    public void readAllWaybill() {
        List<SelectListWaybillOutput> outputList = waybillDao.readWaybill();

        for (SelectListWaybillOutput output : outputList) {
            System.out.println("-".repeat(50));
            System.out.println("운송장 번호: " + output.id());
            System.out.println("상품 이름: " + output.productName());
            System.out.println("-- 보낸 이 --");
            System.out.println("창고 주소: " + output.warehouseLocation());
            System.out.println("-- 받는 이 --");
            System.out.println("고객 이름: " + output.customerName());
            System.out.println("고객 주소: " + output.customerAddress());
            System.out.println("-".repeat(50));
        }
    }
}
