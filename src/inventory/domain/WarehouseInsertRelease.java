package inventory.domain;

import lombok.Data;

import java.util.Date;

@Data
public class WarehouseInsertRelease {
    // 입출고 id
    private Long id;
    // 수량
    private Long quantity;
    // 총가격
    private Long totalPrice;
    // 날짜(입고일, 출고일)
    private Date date;
    //상품id
    private Long productid;


    // 입출고 타입
    private InsertReleaseType type; // TODO : enum으로 변경


}
