package com.ssg.wsmt.inventory.dto;

import com.ssg.wsmt.inventory.enums.WhType;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.mapping.Column;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Data
public class WarehouseDTO {

    // 창고 id
    private Long id;
    // 창고 타입
    private WhType type;
    // 지역
    private String location;
    //택배사
    @Column("DELIVERY_CMP_ID")
    private Long deliveryId;
    @Column("TYPE")
    private String sectionType;
    private String deliveryCompanyName;

}
