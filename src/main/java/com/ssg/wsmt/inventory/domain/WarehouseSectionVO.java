package com.ssg.wsmt.inventory.domain;

import com.ssg.wsmt.inventory.enums.WhSectionType;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class WarehouseSectionVO {
    // 창고 구역 id
    private Long id;
    // 창고 구역 이름
    private String name;
    // 창고 id (FK)
    private Long warehouseId;
    private WhSectionType type;
}
