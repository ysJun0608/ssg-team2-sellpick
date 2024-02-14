package delivery.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Waybill {
    // 운송장 번호
    private Long id;
    // 운송 시작 날짜
    private LocalDateTime delivery_At;
    // 주문 id
    private Long orders_id;
    // 고객 id
    private Long customer_id;
}
