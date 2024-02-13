package smOrders.domain;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class SMOrders {
    // 주문 id
    private int id;

    //주문 수량
    private int quantity;

    // 결제 금액
    private int payment_amount;

    //주문 일자
    private Date created_at;

    //예상 배송일
    private Date expected_at;

    //판매자 발송 상태(배송준비중, 주문 취소, 배송완료)
    private String seller_send_status;

    //고객 ID(FK)
    private int customer_id;

    //쇼핑몰 ID(FK)
    private int shopping_mall_id;

    //상품 ID(FK)
    private int products_id;



}
