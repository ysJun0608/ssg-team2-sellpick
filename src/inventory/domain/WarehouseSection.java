package inventory.domain;

import inventory.enums.WhSectionType;
import lombok.Data;

@Data
public class WarehouseSection {
    // 창고 구역 id
    private Long id;
    // 창고 구역 이름
    private String name;
    // 창고 id (FK)
    private Long warehouseId;
    private WhSectionType type;
}
