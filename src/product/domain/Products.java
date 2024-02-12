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
    private Long brand_Id;
    // 사업자 id
    private Long Owner_Id;
    // 창고 id
    private Long warehouse_Id;

    // GETTER, SETTER
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

    public Long getBrand_Id() {
        return brand_Id;
    }

    public void setBrand_Id(Long brand_Id) {
        this.brand_Id = brand_Id;
    }

    public Long getOwner_Id() {
        return Owner_Id;
    }

    public void setOwner_Id(Long owner_Id) {
        Owner_Id = owner_Id;
    }

    public Long getWarehouse_Id() {
        return warehouse_Id;
    }

    public void setWarehouse_Id(Long warehouse_Id) {
        this.warehouse_Id = warehouse_Id;
    }
}