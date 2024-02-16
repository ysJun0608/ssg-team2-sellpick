package mgtOrders.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mgtOrders.enums.MgtOrdersStatus;
import org.w3c.dom.ls.LSOutput;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MgtOrders {
    // 발주 id
    private Long id;
    // 매입 거래처
    private String purchaser;
    // 발주 확정 여부 (발주 요청 / 발주 확정 / 배송 완료)
    private MgtOrdersStatus status;
    // 발주 일자
    private LocalDateTime createdAt;
    // 창고 id (FK)
    private Long warehouseId;

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

    public MgtOrdersStatus getStatus() {
        return status;
    }

    public void setStatus(MgtOrdersStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }
}
