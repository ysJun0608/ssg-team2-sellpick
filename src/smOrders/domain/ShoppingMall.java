package smOrders.domain;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ShoppingMall {
    // 쇼핑몰 id
    private Long id;
    // 쇼핑몰 명
    private String name;

}
