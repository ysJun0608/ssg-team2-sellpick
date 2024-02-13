package mgtOrders.domain;

public class MgtOrderProductsRelationship {
    // 발주 상품 관계 id
    private Long id;

    @Override
    public String toString() {
        return "MgtOrderProductsRelationship{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", productsId=" + productsId +
                ", mgtOrdersId=" + mgtOrdersId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getProductsId() {
        return productsId;
    }

    public void setProductsId(Long productsId) {
        this.productsId = productsId;
    }

    public Long getMgtOrdersId() {
        return mgtOrdersId;
    }

    public void setMgtOrdersId(Long mgtOrdersId) {
        this.mgtOrdersId = mgtOrdersId;
    }

    // 수량
    private Long quantity;
    // 상품 id
    private Long productsId;
    // 발주 id
    private Long mgtOrdersId;
}