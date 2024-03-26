package com.ssg.wsmt.inventory.domain;

import com.ssg.wsmt.product.dto.ProductDTO;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

@Data
public class InventoryVO {
    // 재고 id
    @Id
    private Long id;
    // 재고 수량
    private int quantity;
    // 창고 구역 id (FK)
    @Column("wh_section_id")
    private Long whSectionId;
    // 재고 상품 id (FK)
    @Column("product_id")
    private Long productId;

}

