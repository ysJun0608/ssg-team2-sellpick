package smOrders.domain;

import java.util.Date;

public class SMOrders {
    // 주문 id
    private int id;

    //주문 수량
    private int quantity;

    // 결제 금액
    private int payment_amount;

    //주문 일자
    private Date created_at;

    //예상 배송일
    private Date expected_at;

    //판매자 발송 상태(배송준비중, 주문 취소, 배송완료)
    private String seller_send_status;

    //고객 ID(FK)
    private int customer_id;

    //쇼핑몰 ID(FK)
    private int shopping_mall_id;

    //상품 ID(FK)
    private int products_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPayment_amount() {
        return payment_amount;
    }

    public void setPayment_amount(int payment_amount) {
        this.payment_amount = payment_amount;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getExpected_at() {
        return expected_at;
    }

    public void setExpected_at(Date expected_at) {
        this.expected_at = expected_at;
    }

    public String getSeller_send_status() {
        return seller_send_status;
    }

    public void setSeller_send_status(String seller_send_status) {
        this.seller_send_status = seller_send_status;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
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

    @Override
    public String toString() {
        return "SMOrders{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", payment_amount=" + payment_amount +
                ", created_at=" + created_at +
                ", expected_at=" + expected_at +
                ", seller_send_status='" + seller_send_status + '\'' +
                ", customer_id=" + customer_id +
                ", shopping_mall_id=" + shopping_mall_id +
                ", products_id=" + products_id +
                '}';
    }
}
