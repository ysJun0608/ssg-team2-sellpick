package smOrders.service.impl;

import smOrders.dao.SMOrdersDao;
import smOrders.domain.SellerSendStatus;
import smOrders.domain.smOrders;
import smOrders.dto.SmOrdersOutput;
import smOrders.service.SmOrdersService;

import java.util.List;
import java.util.Scanner;

public class SmOrderServiceImpl implements SmOrdersService {
    public SMOrdersDao smordersdao = new SMOrdersDao();
    static Scanner sc = new Scanner(System.in);

    @Override
    public void createOrder() {
        System.out.println("주문을 생성합니다.");

        smOrders smOrders = new smOrders(); // 객체를 생성해줍니다.

        // SERVICE 로 빠져야해요 ==========
        System.out.print("주문 ID: ");
        String order_id = sc.nextLine();

        System.out.print("주문 수량: ");
        String quantity = sc.nextLine();
        //주문 일자 // 예상 배송일
        System.out.print("판매자 발송 상태(배송준비중, 주문 취소, 배송완료): ");
        //판매자 발송 상태
        System.out.print("판매자 발송 상태 (1. PREPARING, 2. COMPLETE, 3. CANCEL) : ");
        String num = sc.nextLine();

        String status = null;
        switch (num) {
            case "1" -> status = "PREPARING";
            case "2" -> status = "COMPLETE";
            case "3" -> status = "CANCEL";
        }

        System.out.print("고객 ID(FK): ");
        String customer_id = sc.nextLine();

        System.out.print("쇼핑몰 ID(FK): ");
        String shopping_mall_id = sc.nextLine();

        System.out.print("상품 ID(FK): ");
        String products_id = sc.nextLine();
        // ======================

//        smOrders.setSellerSendStatus(status);
        smOrders.setId(Long.valueOf(order_id));
        smOrders.setQuantity(Integer.parseInt(quantity));
        smOrders.setQuantity(Integer.parseInt(quantity));
        smOrders.setStatus(SellerSendStatus.valueOf(status));
        smOrders.setCustomerId(Long.valueOf(customer_id));
        smOrders.setShoppingMallId(Long.valueOf(shopping_mall_id));
        smOrders.setProductId(Long.valueOf(products_id));

        smordersdao.insertSmOrdersStatus(smOrders);
        System.out.println("주문이 생성되었습니다.");
    }


    @Override
    public void updateStatus() {
        readOrder();
        smOrders smOrders = readOne();

        // SERVICE 로 빠져야해요 ==========
        //판매자 발송 상태
        System.out.print("판매자 발송 상태 (1. PREPARING, 2. COMPLETE, 3. CANCEL) : ");
        String num = sc.nextLine();

        String status = null;
        switch (num) {
            case "1" -> status = "PREPARING";
            case "2" -> status = "COMPLETE";
            case "3" -> status = "CANCEL";
        }
        // ======================
        smOrders.setStatus(SellerSendStatus.valueOf(status));
//        smOrders.setSellerSendStatus(status);

        smordersdao.updateSmOrdersStatus(smOrders);
    }

    @Override
    public smOrders readOne() {
        readOrder();
        System.out.println("선택");
        Long id = Long.valueOf(sc.nextLine());

        smOrders smOrders = smordersdao.findById(id);

        return smOrders;
    }

    // 주문 하나 출력
    @Override
    public void readOrder() {
        //주문 정보를 출력하는 부분
        System.out.println("[주문을 조회합니다.]");
        List<SmOrdersOutput> outputList = smordersdao.smOrdersReadAllCanCel();
        for (SmOrdersOutput output : outputList) {
            // 배송 준비 중인 주문 정보 출력
            System.out.println("주문 ID: " + output.id());
            System.out.println("주문 수량: " + output.quantity());
            System.out.println("주문 생성일: " + output.createdAt());
            System.out.println("예상 배송일: " + output.expectedAt());
            System.out.println("판매자 발송 상태: " + output.sellerSendStatus());
            System.out.println("고객 ID: " + output.customerId());
            System.out.println("쇼핑몰 ID: " + output.shoppingMallId());
            System.out.println("쇼핑몰 이름: " + output.shoppingMallName());
            System.out.println("상품 ID: " + output.productsId());
            System.out.println("상품 이름: " + output.productName());
            System.out.println("상품 가격: " + output.productPrice());
            System.out.println("브랜드 이름: " + output.brandName());
            System.out.println("사업자 ID: " + output.businessOwnerId());
            System.out.println("사업자 이름: " + output.ownerName());
            System.out.println("사업자 전화번호: " + output.ownerPhone());
            System.out.println("창고 위치: " + output.location());
            System.out.println("고객 이름: " + output.customerName());
            System.out.println("고객 전화번호: " + output.customerPhone());
            System.out.println("=".repeat(50));
        }

        System.out.print("번호 선택");
        String num = null;
    }


    //주문 취소 출력
    @Override
    public List<smOrders> readAllCanceledOrders() {
        System.out.println("취소된 주문을 조회합니다.");
        List<SmOrdersOutput> outputList = smordersdao.readAllCanceledOrders();
        for (SmOrdersOutput output : outputList) {
            // 배송 준비 중인 주문 정보 출력
            System.out.println("주문 ID: " + output.id());
            System.out.println("주문 수량: " + output.quantity());
            System.out.println("주문 생성일: " + output.createdAt());
            System.out.println("예상 배송일: " + output.expectedAt());
            System.out.println("판매자 발송 상태: " + output.sellerSendStatus());
            System.out.println("고객 ID: " + output.customerId());
            System.out.println("쇼핑몰 ID: " + output.shoppingMallId());
            System.out.println("쇼핑몰 이름: " + output.shoppingMallName());
            System.out.println("상품 ID: " + output.productsId());
            System.out.println("상품 이름: " + output.productName());
            System.out.println("상품 가격: " + output.productPrice());
            System.out.println("브랜드 이름: " + output.brandName());
            System.out.println("사업자 ID: " + output.businessOwnerId());
            System.out.println("사업자 이름: " + output.ownerName());
            System.out.println("사업자 전화번호: " + output.ownerPhone());
            System.out.println("창고 위치: " + output.location());
            System.out.println("고객 이름: " + output.customerName());
            System.out.println("고객 전화번호: " + output.customerPhone());
            System.out.println("=".repeat(50));
        }

        System.out.println("번호 선택");
        String num = null;

        return null;
    }

    @Override
    public List<smOrders> readAllPreparedOrders() {
        System.out.println("배송준비중인 주문을 조회합니다.");
        List<SmOrdersOutput> outputList = smordersdao.readAllPreparedOrders();

        for (SmOrdersOutput output : outputList) {
            // 배송 준비 중인 주문 정보 출력
            System.out.println("주문 ID: " + output.id());
            System.out.println("주문 수량: " + output.quantity());
            System.out.println("주문 생성일: " + output.createdAt());
            System.out.println("예상 배송일: " + output.expectedAt());
            System.out.println("판매자 발송 상태: " + output.sellerSendStatus());
            System.out.println("고객 ID: " + output.customerId());
            System.out.println("쇼핑몰 ID: " + output.shoppingMallId());
            System.out.println("쇼핑몰 이름: " + output.shoppingMallName());
            System.out.println("상품 ID: " + output.productsId());
            System.out.println("상품 이름: " + output.productName());
            System.out.println("상품 가격: " + output.productPrice());
            System.out.println("브랜드 이름: " + output.brandName());
            System.out.println("사업자 ID: " + output.businessOwnerId());
            System.out.println("사업자 이름: " + output.ownerName());
            System.out.println("사업자 전화번호: " + output.ownerPhone());
            System.out.println("창고 위치: " + output.location());
            System.out.println("고객 이름: " + output.customerName());
            System.out.println("고객 전화번호: " + output.customerPhone());
            System.out.println("=".repeat(50));
        }

        return null;
    }
}