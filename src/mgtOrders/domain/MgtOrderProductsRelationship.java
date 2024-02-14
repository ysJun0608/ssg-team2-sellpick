package mgtOrders.domain;

import lombok.Data;

@Data
public class MgtOrderProductsRelationship {
    // 발주 상품 관계 id
    private Long id;

    // 수량
    private int quantity;

    // 상품 id
    private Long productsId;

    // 발주 id
    private Long mgtOrdersId;
}
