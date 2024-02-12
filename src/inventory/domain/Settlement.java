package inventory.domain;

import java.time.LocalDateTime;

public class Settlement {
    // 마감 id
    private Long id;
    // 입고 수량
    private Long insertQuantity;
    // 출고 수량
    private Long releaseQuantity;
    // 최종 금액
    private Long totalPrice;
    // 생성 날짜
    private LocalDateTime createdAt;
    // 수정 날짜
    private LocalDateTime modifiedAt;

    // GETTER, SETTER
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInsertQuantity() {
        return insertQuantity;
    }

    public void setInsertQuantity(Long insertQuantity) {
        this.insertQuantity = insertQuantity;
    }

    public Long getReleaseQuantity() {
        return releaseQuantity;
    }

    public void setReleaseQuantity(Long releaseQuantity) {
        this.releaseQuantity = releaseQuantity;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
