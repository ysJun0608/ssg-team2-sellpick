package com.ssg.wsmt.inventory.dto;

import com.ssg.wsmt.inventory.enums.WhInOutType;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Data
public class WarehouseInOutDTO {
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
