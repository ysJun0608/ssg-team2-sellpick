package inventory.domain;

import inventory.enums.WhInOutType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WarehouseInsertRelease {
    // 입출고 id
    private Long id;
    // 수량
    private Long quantity;
    // 날짜(입고일, 출고일)
    private LocalDateTime createdAt;
    // 입출고 타입
    private WhInOutType type;
    // 총가격
    private Long totalPrice;
    // 재고 id (FK)
    private Long inventoryId;

}
