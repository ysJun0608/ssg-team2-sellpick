package product.enums;

public enum ProductsStatus {
    ON_SALE("판매 중"),
    STOP_SALE("판매 중지");

    private String desc;

    ProductsStatus(String desc) {
        this.desc = desc;
    }
}
