package smOrders.domain;

public class SMOrders {
    // 주문 id
    private Long id;
    // 상품 수량
    private int quantity;
    // 발송 예정일
    private String shipping_date;
    // 주문일
    private String order_date;
    // 주문자(고객) id
    private Long customer_id;
    // 쇼핑몰 id
    private Long shopping_mall_id;
    // 상품 id
    private Long product_id;
}
