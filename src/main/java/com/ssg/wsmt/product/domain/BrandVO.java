package com.ssg.wsmt.product.domain;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BrandVO{
    // 브랜드 id
    private Long id;
    // 브랜드 이름
    private String name;
}
