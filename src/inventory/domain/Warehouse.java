package inventory.domain;

import lombok.Data;

@Data
public class Warehouse {
    // 창고 id
    private Long id;
    // 창고 타입
    private Type type;
    //private String type; // TODO : enum으로 변경
    // 지역
    private Long delivery_id;
    private String location;

}
