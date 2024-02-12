package smOrders.domain;

import lombok.Data;

@Data
public class ShoppingMall {
    // 쇼핑몰 id
    private Long id;
    // 쇼핑몰 명
    private String name;

    // GETTER, SETTER
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
