package inventory.domain;

import lombok.Data;

@Data
public class WhSmRelationship {
    // 창고-쇼핑몰 관계 id
    private Long id;
    // 창고 id (FK)
    private Long warehouseId;
    // 쇼핑몰 id (FK)
    private Long shoppingMallId;
}
