package smOrders.dto;

import lombok.Builder;

import java.util.Date;

public record SmOrdersOutput(Long id, // order_id
                             int quantity,
                             int paymentAmount,
                             Date createdAt,
                             Date expectedAt,
                             String sellerSendStatus,
                             Long customerId,
                             Long shoppingMallId,
                             String shoppingMallName, // 쇼핑몰
                             Long productsId,
                             String productName,
                             int productPrice,

                             String brandName,
                             Long businessOwnerId,
                             String ownerName,
                             String ownerPhone,
                             String location, // 창고장소
                             String customerName,
                             String customerPhone
                             ) {
    public SmOrdersOutput(Long id, int quantity, int paymentAmount, Date createdAt, Date expectedAt, String sellerSendStatus, Long customerId, Long shoppingMallId, String shoppingMallName, Long productsId, String productName, int productPrice, String brandName, Long businessOwnerId, String ownerName, String ownerPhone, String location, String customerName, String customerPhone) {
        this.id = id;
        this.quantity = quantity;
        this.paymentAmount = paymentAmount;
        this.createdAt = createdAt;
        this.expectedAt = expectedAt;
        this.sellerSendStatus = sellerSendStatus;
        this.customerId = customerId;
        this.shoppingMallId = shoppingMallId;
        this.shoppingMallName = shoppingMallName;
        this.productsId = productsId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.brandName = brandName;
        this.businessOwnerId = businessOwnerId;
        this.ownerName = ownerName;
        this.ownerPhone = ownerPhone;
        this.location = location;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
    }
}
