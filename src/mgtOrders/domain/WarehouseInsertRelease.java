package mgtOrders.domain;

import inventory.enums.WhInOutType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WarehouseInsertRelease {
    // 발주 상품 관계 id
    private Long id;

    // 수량
    private int quantity;

    // 상품 id
    private Long productsId;

    // 발주 id
    private Long mgtOrdersId;

    // 창고 입출고 상태
    private WhInOutType whInOutType;

    // 창고 입출고 날짜
    private LocalDateTime createdAt;

    // 총가격
    private int totalPrice;
}
