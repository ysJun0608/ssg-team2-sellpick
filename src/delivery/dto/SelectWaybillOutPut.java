package delivery.dto;

public record SelectWaybillOutPut(Long id,
                                  Long ordersId,
                                  String productName,
                                  String shoppingMallName,
                                  String warehouseLocation,
                                  String businessPhone,
                                  Long customerId,
                                  String customerName,
                                  String customerPhone,
                                  String customerAddress) {

    public SelectWaybillOutPut(Long id, Long ordersId, String productName, String shoppingMallName, String warehouseLocation, String businessPhone, Long customerId, String customerName, String customerPhone, String customerAddress) {
        this.id = id;
        this.ordersId = ordersId;
        this.productName = productName;
        this.shoppingMallName = shoppingMallName;
        this.warehouseLocation = warehouseLocation;
        this.businessPhone = businessPhone;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
    }
}
