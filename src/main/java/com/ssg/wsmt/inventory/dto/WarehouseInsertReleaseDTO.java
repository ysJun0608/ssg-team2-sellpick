package com.ssg.wsmt.inventory.dto;

import com.ssg.wsmt.inventory.enums.WhInOutType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WarehouseInsertReleaseDTO {
    // 입출고 id
    private Long id;
    // 수량
    private Long quantity;
    // 날짜(입고일, 출고일)
    private LocalDateTime createdAt;
    // 입출고 타입
    private String type;
    // 관리 주문 id (FK)
    private Long fkId;
    // 총가격
    private Long totalPrice;
    // 재고 id (FK)
    private Long productsId;
    private String productName;

}
