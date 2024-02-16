package product.dto;

import lombok.Builder;
import product.enums.ProductsStatus;

public record ProductsOutput(
        Long id,
        // 상품 이름
        String name,
        // 상품 판매 상태(판매 중 / 중지)
        ProductsStatus status,
        // 판매가
        int price,
        // 원가
        int cost,
        // 브랜드 id
        Long brandId,
        // 사업자 id
        Long businessOwnerId,
        // 브랜드 이름
        String brandName
) {
    @Builder
    public ProductsOutput(Long id, String name, ProductsStatus status, int price, int cost, Long brandId, Long businessOwnerId, String brandName) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.price = price;
        this.cost = cost;
        this.brandId = brandId;
        this.businessOwnerId = businessOwnerId;
        this.brandName = brandName;
    }
}
