package com.ssg.wsmt.inventory.dto;

import lombok.*;

import java.time.LocalDateTime;
@ToString
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SettlementDTO {
    // 마감 id
    private Long id;
    // 입고 수량
    private Long insertQuantity;
    // 출고 수량
    private Long releaseQuantity;
    // 최종 금액
    private Long totalPrice;
    // 생성 날짜
    private LocalDateTime createdAt;
    // 수정 날짜
    private LocalDateTime modifiedAt;
}