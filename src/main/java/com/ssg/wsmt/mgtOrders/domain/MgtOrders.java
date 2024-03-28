package com.ssg.wsmt.mgtOrders.domain;

import  com.ssg.wsmt.mgtOrders.enums.MgtOrdersStatus;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MgtOrders {
    // 발주 id
    private Long id;
    // 매입 거래처
    private String purchaser;
    // 발주 확정 여부 (발주 요청 / 발주 확정 / 배송 완료)
    private MgtOrdersStatus status;
    // 발주 일자
    private LocalDateTime createdAt;
    // 창고 id (FK)
    private Long warehouseId;
}
