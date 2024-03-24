package com.ssg.wsmt.inventory.domain;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class WhSmRelationshipVO {
    // 창고-쇼핑몰 관계 id
    private Long id;
    // 창고 id (FK)
    private Long warehouseId;
    // 쇼핑몰 id (FK)
    private Long shoppingMallId;
}
