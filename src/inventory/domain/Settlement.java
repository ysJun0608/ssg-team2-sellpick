package inventory.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
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
}
