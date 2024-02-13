package delivery.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Waybill {
    // 운송장 번호
    private Long id;
    // 운송 시작 날짜
    private LocalDateTime deliveryAt;
    // 주문 id
    private Long ordersId;
}
