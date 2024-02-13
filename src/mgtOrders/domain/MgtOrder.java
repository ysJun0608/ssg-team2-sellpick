package mgtOrders.domain;

public class MgtOrder {
    @Override
    public String toString() {
        return "MgtOrder{" +
                "id=" + id +
                ", purchaser='" + purchaser + '\'' +
                ", confirm='" + confirm + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(String purchaser) {
        this.purchaser = purchaser;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    // 발주 id
    private Long id;
    // 매입 거래처
    private String purchaser;
    // 발주 확정 여부
    private String confirm;
    // 발주 일자
    private String createdAt;
}