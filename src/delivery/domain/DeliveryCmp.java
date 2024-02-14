package delivery.domain;

import lombok.Data;

@Data
// 더미 데이터 만들어서 사용
public class DeliveryCmp {
    // 택배사 id
    private Long id;
    // 택배사 이름
    private String name;
}
