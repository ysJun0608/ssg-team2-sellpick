package product.domain;

public class Products {
    private int id;
    private boolean status;
    private Integer cost;
    private int quantity;
    private Integer price;
    private Integer brandCodeId;
    private int ownerId;
    private int warehouseId;

    public Products(int id, boolean status, Integer cost, int quantity, Integer price, Integer brandCodeId, int ownerId, int warehouseId) {
        this.id = id;
        this.status = status;
        this.cost = cost;
        this.quantity = quantity;
        this.price = price;
        this.brandCodeId = brandCodeId;
        this.ownerId = ownerId;
        this.warehouseId = warehouseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getBrandCodeId() {
        return brandCodeId;
    }

    public void setBrandCodeId(Integer brandCodeId) {
        this.brandCodeId = brandCodeId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", status=" + status +
                ", cost=" + cost +
                ", quantity=" + quantity +
                ", price=" + price +
                ", brandCodeId=" + brandCodeId +
                ", ownerId=" + ownerId +
                ", warehouseId=" + warehouseId +
                '}';
    }
}