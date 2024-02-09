package delivery.domain;

import java.time.LocalDate;

public class Waybill {
    // 운송장 번호
    private Long id;
    // 박스 요금
    private int box_price;
    // 거리 요금
    private int distance_price;
    // 운송 시작 날짜
    private LocalDate start_date;
    // 택배사 id
    private Long delivery_companies_id;
    // 주문 id
    private Long orders_id;
}
