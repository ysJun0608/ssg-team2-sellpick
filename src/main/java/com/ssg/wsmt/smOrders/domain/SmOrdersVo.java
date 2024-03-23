package com.ssg.wsmt.smOrders.domain;

import com.ssg.wsmt.smOrders.enums.SellerSendStatus;
import jakarta.persistence.Column;
import lombok.*;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SmOrdersVo {
    // 주문 id
    @Column(name = "id")
    private Long id;
    // 상품 수량
    @Column(name = "quantity")
    private int quantity;
    // 결제 금액
    @Column(name = "payment_amount")
    private int paymentAmount;
    // 주문일(주문일이 결국 주문 테이블에 튜플 생기는 것과 같으니 createdAt으로 변수이름 사용)
    @Column(name = "created_at")
    private String createdAt;
    // 발송 예정일
    @Column(name = "expected_at")
    private String expectedAt;
    // 판매자 발송 상태(배송준비중, 주문 취소, 배송완료)
    @Column(name = "seller_send_status")
    private SellerSendStatus seller_send_status;
    // 주문자(고객) id
    @Column(name = "customer_ID")
    private Long customerId;
    // 쇼핑몰 id
    @Column(name = "shoppingMall_ID")
    private Long shoppingMallId;
    // 상품 id
    @Column(name = "product_ID")
    private Long productId;

    @Column(name = "name")
    private String shoppingMallName;


}