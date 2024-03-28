package com.ssg.wsmt.mgtOrders.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MgtOrdersProductsRelationshipDTO {

    private Long id;
    private Long quantity;
    private Long productsId;
    private Long mgtOrdersId;
}
