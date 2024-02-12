package smOrders.domain;

import java.util.Date;

public class SMOrders {
    // 주문 id
    private int id;
    // 쇼핑몰 id
    private int shopping_mall_id;
    //상품_id
    private int products_id;
    //고객_id
    private int customer_id;
    //주문일
    private Date CREATED_AT;
    //발송예정일
    private Date Expected_at;
    //상품수량
    private int amount;
    //결제 금액
    private int payment_amount;
    //판매자발송확인
    private String seller_send_statis;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShopping_mall_id() {
        return shopping_mall_id;
    }

    public void setShopping_mall_id(int shopping_mall_id) {
        this.shopping_mall_id = shopping_mall_id;
    }

    public int getProducts_id() {
        return products_id;
    }

    public void setProducts_id(int products_id) {
        this.products_id = products_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public Date getCREATED_AT() {
        return CREATED_AT;
    }

    public void setCREATED_AT(Date CREATED_AT) {
        this.CREATED_AT = CREATED_AT;
    }

    public Date getExpected_at() {
        return Expected_at;
    }

    public void setExpected_at(Date expected_at) {
        Expected_at = expected_at;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPayment_amount() {
        return payment_amount;
    }

    public void setPayment_amount(int payment_amount) {
        this.payment_amount = payment_amount;
    }

    public String getSeller_send_statis() {
        return seller_send_statis;
    }

    public void setSeller_send_statis(String seller_send_statis) {
        this.seller_send_statis = seller_send_statis;
    }

    @Override
    public String toString() {
        return "SMOrders{" +
                "id=" + id +
                ", shopping_mall_id=" + shopping_mall_id +
                ", products_id=" + products_id +
                ", customer_id=" + customer_id +
                ", CREATED_AT=" + CREATED_AT +
                ", Expected_at=" + Expected_at +
                ", amount=" + amount +
                ", payment_amount=" + payment_amount +
                ", seller_send_statis='" + seller_send_statis + '\'' +
                '}';
    }
}
