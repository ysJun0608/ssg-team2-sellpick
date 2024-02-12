package delivery.domain;

import java.time.LocalDate;

public class Waybill {
    // 운송장 번호
    private Long id;
    // 운송 시작 날짜
    private LocalDate deliveryAt;
    // 주문 id
    private Long ordersId;

    // GETTER, SETTER
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDeliveryAt() {
        return deliveryAt;
    }

    public void setDeliveryAt(LocalDate deliveryAt) {
        this.deliveryAt = deliveryAt;
    }

    public Long getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Long ordersId) {
        this.ordersId = ordersId;
    }
}
