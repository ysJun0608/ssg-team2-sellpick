package mgtOrders.domain;

public class MgtOrder {
    // 발주 id
    private Long id;
    // 창고 id
    private Long warehouseId;
    // 상품 id
    private Long prductsId;
    // 매입 거래처
    private String purchaser;
    // 발주 구분
    private String orderType;
    // 발주 유형
    private String orderCategory;
    // 수량
    private int quantity;
    // 발주 일자
    private String createdAt;

}
