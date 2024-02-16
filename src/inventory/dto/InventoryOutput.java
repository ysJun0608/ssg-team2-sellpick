package inventory.dto;

import lombok.Builder;

public record InventoryOutput(
        Long id,
        Long quantity,
        Long warehouseSectionId,
        Long productId,
        Long warehouseId,
        String location,
        String productName,
        String brandName,
        int productPrice
) {
    @Builder
    public InventoryOutput(Long id, Long quantity, Long warehouseSectionId, Long productId, Long warehouseId, String location, String productName, String brandName, int productPrice) {
        this.id = id;
        this.quantity = quantity;
        this.warehouseSectionId = warehouseSectionId;
        this.productId = productId;
        this.warehouseId = warehouseId;
        this.location = location;
        this.productName = productName;
        this.brandName = brandName;
        this.productPrice = productPrice;
    }
}
