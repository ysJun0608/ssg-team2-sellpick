package delivery.dto;

public record SelectListWaybillOutput(
        Long id,
        Long ordersId,
        String productName,
        String customerAddress,
        String warehouseLocation,
        String customerName) {
    public SelectListWaybillOutput(Long id, Long ordersId, String productName,String customerAddress, String warehouseLocation, String customerName) {
        this.id = id;
        this.ordersId = ordersId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.productName = productName;
        this.warehouseLocation = warehouseLocation;
    }
}
