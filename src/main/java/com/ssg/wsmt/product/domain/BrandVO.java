package com.ssg.wsmt.product.domain;

import lombok.*;

@ToString
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BrandVO {
    // 브랜드 id
    private Long id;
    // 브랜드 이름
    private String name;
}
