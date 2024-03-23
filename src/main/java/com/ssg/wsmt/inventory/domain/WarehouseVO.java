package com.ssg.wsmt.inventory.domain;

import com.ssg.wsmt.inventory.enums.WhType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseVO {
    // 창고 id
    private Long id;
    // 창고 타입
    private WhType type;
    // 지역
    private String location;
    //택배사
    private Long deliveryId;

}
