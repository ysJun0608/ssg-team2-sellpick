package mgtOrders.domain;

import lombok.Data;
import mgtOrders.enums.MgtOrdersStatus;
@Data
public class MgtOrders {
    // 발주 id
    private Long id;
    // 매입 거래처
    private String purchaser;
    // 발주 확정 여부 (발주 요청 / 발주 확정 / 배송 완료)
    private MgtOrdersStatus status;
    // 발주 일자
    private String createdAt;
    //창고 아이디(FK)
    private Long warehouseId;
}
