package inventory.domain;

import lombok.Data;

@Data
public class Inventory {
    // 재고 id
    private Long id;
    // 재고 수량
    private int quantity;
    // 창고 구역 id (FK)
    private Long whSectionId;
    // 재고 상품 id (FK)
    private Long productId;
}
