package com.ssg.wsmt.inventory.domain;

import com.ssg.wsmt.inventory.enums.WhInOutType;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class WarehouseInsertReleaseVO {
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
