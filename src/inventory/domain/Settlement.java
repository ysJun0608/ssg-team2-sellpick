package inventory.domain;

import java.util.Date;

public class Settlement {
    // 정산 id
    private Long id;
    //입고수량
    private Long in_quantity;
    //출고수량
    private Long out_quantity;
    //금액
    private Long total_price;
    //생성날짜
    private Date create_at;
    //수정날짜
    private Date modified_at;
}
