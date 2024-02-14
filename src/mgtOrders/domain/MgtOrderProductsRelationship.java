package mgtOrders.domain;

public class MgtOrderProductsRelationship {
    // 발주 상품 관계 id
    private Long id;

    // 수량
    private int quantity;

    // 상품 id
    private int productsId;

    // 발주 id
    private int mgtOrdersId;
}
