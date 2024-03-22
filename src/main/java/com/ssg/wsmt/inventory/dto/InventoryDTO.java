package com.ssg.wsmt.inventory.dto;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class InventoryDTO{
        Long id;
        Long quantity;
        Long warehouseSectionId;
        Long productId;
        Long warehouseId;
        String location;
        String productName;
        String brandName;
        int productPrice;
}