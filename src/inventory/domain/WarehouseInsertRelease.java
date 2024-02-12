package inventory.domain;

public class WarehouseInsertRelease {
    // 입출고 id
    private Long id;
    // 수량
    private Long amount;
    // 날짜(입고일, 출고일)
    private String createdAt;
    // 입출고 타입
    private String type; // TODO : enum으로 변경
    // 총가격
    private Long totalPrice;
    // 상품 id (FK)
    private Long productsId;
    // 마감 id (FK)
    private Long settlementId;

}
