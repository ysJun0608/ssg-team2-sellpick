package inventory.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class WarehouseInsertRelease {
    // 입출고 id
    private Long id;
    // 수량
    private Long amount;
    // 날짜(입고일, 출고일)
    private LocalDateTime createdAt;
    private Long totalPrice;
    // 상품 id (FK)
    private Long productsId;
    // 마감 id (FK)
    private Long settlementId;

    // 입출고 타입
    private InsertReleaseType type; // TODO : enum으로 변경


}
