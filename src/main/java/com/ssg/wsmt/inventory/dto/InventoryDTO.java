package com.ssg.wsmt.inventory.dto;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class InventoryDTO{
        private Long id;
        private Long quantity;
        private Long whSectionId;
        private Long productId;
        private Long warehouseId;
        private String location;
        private String productName;
        private String brandName;
        private int productPrice;
        private String productStatus;
        private Long cost;

        private String ownerNAME;
        private Long price;
        private Long quantities;
}