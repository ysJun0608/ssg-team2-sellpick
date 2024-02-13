package mgtOrders.domain;


import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MgtOrder {
    // 발주 id
    private int id;
    // 매입 거래처
    private String purchaser;
    // 발주 상태
    private String status;
    // 발주 일자
    private Date createdAt;
}
