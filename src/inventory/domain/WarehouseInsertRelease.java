package inventory.domain;

public class WarehouseInsertRelease {
    // 입출고 id
    private Long id;
    // 수량
    private Long quantity;
    // 총가격
    private Long totalPrice;
    // 날짜(입고일, 출고일)
    private String date;
    // 창고별상품 id
    private Long warehouseProductId;
    // 입출고 타입
    private String type; // TODO : enum으로 변경

}
