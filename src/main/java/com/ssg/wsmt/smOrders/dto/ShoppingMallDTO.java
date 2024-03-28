package com.ssg.wsmt.smOrders.dto;

import lombok.*;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingMallDTO {
    // 쇼핑몰 id
    private Long id;
    private String name;
    // 기타 필드와 getter, setter
}
