package com.ssg.wsmt.smOrders.domain;

import lombok.*;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingMallVo {
    // 쇼핑몰 id
    private Long id;
    // 쇼핑몰 명
    private String name;
}
