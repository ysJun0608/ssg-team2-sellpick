package smOrders.service.impl;

import smOrders.dao.SMOrdersDao;
import smOrders.domain.smOrders;
import smOrders.dto.SmOrdersAllOutput;
import smOrders.dto.SmOrdersOutput;
import smOrders.enums.SellerSendStatus;
import smOrders.service.SmOrdersService;

import java.util.List;
import java.util.Scanner;

public class SmOrderServiceImpl implements SmOrdersService {
    public SMOrdersDao smordersdao = new SMOrdersDao();
    static Scanner sc = new Scanner(System.in);

    /**
     * 주문을 생성하는 메서드입니다.
     */
    @Override
    public void createOrder() {
        System.out.println("주문을 생성합니다.");
        // 주문 객체 생성
        smOrders smOrders = new smOrders();

//        // 사용자로부터 주문 정보 입력 받기
//        System.out.print("주문 ID: ");
//        String order_id = sc.nextLine();


        System.out.print("고객 ID(FK): ");
        String customer_id = sc.nextLine();

        System.out.print("쇼핑몰 ID(FK): ");
        String shopping_mall_id = sc.nextLine();

        System.out.print("상품 ID(FK): ");
        String products_id = sc.nextLine();

        System.out.print("주문 수량: ");
        String quantity = sc.nextLine();

//        // 판매자 발송 상태 입력
//        System.out.print("판매자 발송 상태 (1. PREPARING, 2. COMPLETE, 3. CANCEL) : ");
//        String num = sc.nextLine();

//        String status = null;
//        switch (num) {
//            case "1" -> status = "PREPARING";
//            case "2" -> status = "COMPLETE";
//            case "3" -> status = "CANCEL";
//        }
        SellerSendStatus status = SellerSendStatus.PREPARING;
        // ======================

        // 주문 객체에 입력된 정보 설정
//        smOrders.setId(Long.valueOf(order_id));
        smOrders.setQuantity(Integer.parseInt(quantity));
        smOrders.setQuantity(Integer.parseInt(quantity));
        smOrders.setStatus(status);
        smOrders.setCustomerId(Long.valueOf(customer_id));
        smOrders.setShoppingMallId(Long.valueOf(shopping_mall_id));
        smOrders.setProductId(Long.valueOf(products_id));

        // 주문 정보 저장
        smordersdao.insertSmOrdersStatus(smOrders);
        System.out.println("주문이 생성되었습니다.");
    }

    /**
     * 주문 상태를 업데이트하는 메서드입니다.
     */
    @Override
    public void updateStatus() {
        // 주문 정보 조회
        readOrder();
        // 특정 주문 조회
        smOrders smOrders = readOne();

        // 판매자 발송 상태 입력 받기
        System.out.print("판매자 발송 상태 (1. PREPARING, 2. COMPLETE, 3. CANCEL) : ");
        String num = sc.nextLine();

        String status = null;
        switch (num) {
            case "1" -> status = "PREPARING";
            case "2" -> status = "COMPLETE";
            case "3" -> status = "CANCEL";
        }

        // 주문 객체의 상태 업데이트
        smOrders.setStatus(SellerSendStatus.valueOf(status));

        // 주문 상태 업데이트
        smordersdao.updateSmOrdersStatus(smOrders);
    }

    @Override
    public SmOrdersAllOutput readOneSelect() {
//        readOrder();
        // 사용자 선택 입력 받기

        System.out.print("조회 주문번호(한개) 선택: ");
        Long id = Long.valueOf(sc.nextLine());
        SmOrdersAllOutput smordersalloutput = smordersdao.readOneAlloutput(id);

        System.out.println("주문 ID: " + smordersalloutput.orderId());
        System.out.println("주문 수량: " + smordersalloutput.quantity());
        System.out.println("결제 금액: " + smordersalloutput.paymentAmount());
        System.out.println("주문 생성일: " + smordersalloutput.createdAt());
        System.out.println("예상 배송일: " + smordersalloutput.expectedAt());
        System.out.println("판매자 발송 상태: " + smordersalloutput.sellerSendStatus());
        System.out.println("쇼핑몰 ID: " + smordersalloutput.shoppingMallId());
        System.out.println("쇼핑몰 이름: " + smordersalloutput.shoppingMallName());
        System.out.println("상품 ID: " + smordersalloutput.productsId());
        System.out.println("상품 이름: " + smordersalloutput.productName());
        System.out.println("브랜드 이름: " + smordersalloutput.brandName());
        System.out.println("=".repeat(50));

        return smordersalloutput;
    }

    /**
     * 특정 주문을 조회하는 메서드입니다.
     * @return 조회된 주문 객체
     */
    @Override
    public smOrders readOne() {
        // 주문 목록 출력
        readOrder();

        System.out.print("번호 선택 : ");
        Long id = Long.valueOf(sc.nextLine());

        // 주문 ID로 조회하여 해당 주문 객체 반환
        smOrders smOrders = smordersdao.findById(id);

        return smOrders;
    }

    /**
     * 모든 주문을 조회하여 출력하는 메서드입니다.
     */
    @Override
    public void readOrder() {
        // 주문 정보를 출력하는 부분
        System.out.println("[주문을 조회합니다.]");

        // 모든 주문 정보를 가져옴
        List<SmOrdersOutput> outputList = smordersdao.smOrdersReadAll();

        if (outputList.isEmpty()) {
            System.out.println("주문이 없습니다.");
            return;
        }

        // 각 주문 정보 출력
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
    }
    /**
     * 취소된 주문을 조회하여 출력하는 메서드입니다.
     *
     * @return 조회된 취소된 주문 목록
     */
    @Override
    public List<smOrders> readAllCanceledOrders() {
        // 취소된 주문 정보를 출력하는 부분
        System.out.println("취소된 주문을 조회합니다.");

        // 취소된 주문 정보를 가져옴
        List<SmOrdersOutput> outputList = smordersdao.readAllCanceledOrders();

        if (outputList.isEmpty()) {
            System.out.println("취소된 주문이 없습니다.");
            return null;
        }

        // 각 취소된 주문 정보 출력
        for (SmOrdersOutput output : outputList) {
            // 취소된 주문 정보 출력
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

    /**
     * 배송 준비 중인 주문을 조회하여 출력하는 메서드입니다.
     *
     * @return 조회된 배송 준비 중인 주문 목록
     */
    @Override
    public List<smOrders> readAllPreparedOrders() {
        // 배송 준비 중인 주문 정보를 출력하는 부분
        System.out.println("배송준비중인 주문을 조회합니다.");

        // 배송 준비 중인 주문 정보를 가져옴
        List<SmOrdersOutput> outputList = smordersdao.readAllPreparedOrders();

        if (outputList.isEmpty()) {
            System.out.println("배송 준비 중인 주문이 없습니다.");
            return null;
        }

        // 각 배송 준비 중인 주문 정보 출력
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