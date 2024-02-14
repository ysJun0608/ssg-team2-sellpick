package mgtOrders.domain;

public class MgtOrdersProductsRelationship {
    // 발주 상품 관계 id
    private Long id;
    // 수량
    private int quantity;
    // 상품 id (FK)
    private Long productsId;
    // 발주 id (FK)
    private Long mgtOrdersId;
}
