package delivery.service.impl;


import delivery.dao.WaybillDao;
import delivery.dto.SelectWaybillOutPut;
import delivery.service.WaybillService;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class WaybillServiceImpl implements WaybillService {
    Scanner sc = new Scanner(System.in);
    WaybillDao waybillDao = new WaybillDao();
    Connection conn = null;

    public void insertWaybill() {
        System.out.println("주문 번호를 입력해주세요: ");
        Long ordersNum = Long.parseLong(sc.nextLine());
        Long waybillId = waybillDao.saveWaybill(ordersNum);

        System.out.println("운송장 pk번호 : " + waybillId);
    }

    @Override
    public void selectWaybill(Long ordersNum) {

        List<SelectWaybillOutPut> outputList = waybillDao.updateWaybill(ordersNum);

        for (SelectWaybillOutPut output : outputList) {
            System.out.println("------------------------");
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

            System.out.println("------------------------");
        }
    }
}
