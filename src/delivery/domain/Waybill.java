package delivery.domain;

import java.time.LocalDate;

public class Waybill {
    // 운송장 번호
    private Long id;
    // 운송 시작 날짜
    private LocalDate delivery_At;
    // 주문 id
    private Long orders_id;
    // 고객 id
    private Long customer_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDelivery_At() {
        return delivery_At;
    }

    public void setDelivery_At(LocalDate delivery_At) {
        this.delivery_At = delivery_At;
    }

    public Long getOrders_id() {
        return orders_id;
    }

    public void setOrders_id(Long orders_id) {
        this.orders_id = orders_id;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    @Override
    public String toString() {
        return "Waybill{" +
                "id=" + id +
                ", delivery_At=" + delivery_At +
                ", orders_id=" + orders_id +
                ", customer_id=" + customer_id +
                '}';
    }
}
