package product.domain;

public class Products {
    // 상품 id
    private Long id;
    // 상품 이름
    private String name;
    // 상품 판매 상태(판매 중 / 중지)
    private boolean status;
    // 판매가
    private int price;
    // 원가
    private int cost;
    // 상품 수량
    private int quantity;
    // 브랜드 id
    private Long brandId;
    // 사업자 id
    private Long businessOwnerId;
    // 창고 구역 id
    private Long whSectionId;
}
