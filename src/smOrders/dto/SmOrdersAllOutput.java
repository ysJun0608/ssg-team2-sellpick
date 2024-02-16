package smOrders.dto;

import java.time.LocalDateTime;
import java.util.Date;

public record SmOrdersAllOutput(Long orderId,
                                int quantity,
                                int paymentAmount,
                                LocalDateTime createdAt,
                                LocalDateTime expectedAt,
                                String sellerSendStatus,
                                Long shoppingMallId,
                                String shoppingMallName, // 쇼핑몰
                                Long productsId,
                                String productName,
                                String brandName) {
    public SmOrdersAllOutput(Long orderId, int quantity, int paymentAmount, LocalDateTime createdAt, LocalDateTime expectedAt, String sellerSendStatus, Long shoppingMallId, String shoppingMallName, Long productsId, String productName, String brandName) {
        this.orderId = orderId;
        this.quantity = quantity;
        this.paymentAmount = paymentAmount;
        this.createdAt = createdAt;
        this.expectedAt = expectedAt;
        this.sellerSendStatus = sellerSendStatus;
        this.shoppingMallId = shoppingMallId;
        this.shoppingMallName = shoppingMallName;
        this.productsId = productsId;
        this.productName = productName;
        this.brandName = brandName;
    }
}