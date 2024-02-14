package smOrders.domain;

import lombok.Data;
import smOrders.enums.SellerSendStatus;

@Data
public class smOrders {
    // 주문 id
    private Long id;
    // 상품 수량
    private int quantity;
    // 결제 금액
    private int paymentAmount;
    // 주문일(주문일이 결국 주문 테이블에 튜플 생기는 것과 같으니 createdAt으로 변수이름 사용)
    private String createdAt;
    // 발송 예정일
    private String expectedAt;
    // 판매자 발송 상태(배송준비중, 주문 취소, 배송완료)
    private SellerSendStatus status;
    // 주문자(고객) id
    private Long customerId;
    // 쇼핑몰 id
    private Long shoppingMallId;
    // 상품 id
    private Long productId;
}