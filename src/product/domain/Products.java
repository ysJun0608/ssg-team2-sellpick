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
    // 브랜드 id
    private Long brandId;
    // 사업자 id
    private Long businessOwnerId;
    // 창고 id
    private String warehouseSectionName;
    private String warehouseLocation;
    private int quantity;

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", price=" + price +
                ", cost=" + cost +
                ", brandId=" + brandId +
                ", businessOwnerId=" + businessOwnerId +
                ", warehouseSectionName='" + warehouseSectionName + '\'' +
                ", warehouseLocation='" + warehouseLocation + '\'' +
                '}';
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getBusinessOwnerId() {
        return businessOwnerId;
    }

    public void setBusinessOwnerId(Long businessOwnerId) {
        this.businessOwnerId = businessOwnerId;
    }

    public String getWarehouseSectionName() {
        return warehouseSectionName;
    }

    public void setWarehouseSectionName(String warehouseSectionName) {
        this.warehouseSectionName = warehouseSectionName;
    }

    public String getWarehouseLocation() {
        return warehouseLocation;
    }

    public void setWarehouseLocation(String warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }
}