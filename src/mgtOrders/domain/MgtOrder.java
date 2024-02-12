package mgtOrders.domain;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MgtOrder {
    // 발주 id
    private Long id;

    // 발주 일자
    private String createdAt;

    // 매입 거래처
    private String purchaser;

    // 상품 id
    private Long productsId;

    // 수량
    private int quantity;

    // 확정여부
    private boolean confirm;

//    // 창고 id
//    private Long warehouseId;
//
//    // 발주 구분
//    private String orderType;
//
//    // 발주 유형
//    private String orderCategory;
}
