package smOrders.domain;

public class SMOrders {
    // 주문 id
    private Long id;
    // 상품 수량
    private int quantity;
    // 결제 금액
    private int paymentAmount;
    // 주문일(주문일이 결국 주문 테이블에 튜플 생기는 것과 같으니 createdAt으로 변수이름 사용)
    private String createdAt;
    // 발송 예정일
    private String expectedAt;
    // 판매자 발송 상태(배송준비중, 주문 취소, 배송완료)
    private String sellerSendStatus; // TODO : enum으로 변경
    // 주문자(고객) id
    private Long customerId;
    // 쇼핑몰 id
    private Long shoppingMallId;
    // 상품 id
    private Long productId;

    // GETTER, SETTER
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getExpectedAt() {
        return expectedAt;
    }

    public void setExpectedAt(String expectedAt) {
        this.expectedAt = expectedAt;
    }

    public String getSellerSendStatus() {
        return sellerSendStatus;
    }

    public void setSellerSendStatus(String sellerSendStatus) {
        this.sellerSendStatus = sellerSendStatus;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getShoppingMallId() {
        return shoppingMallId;
    }

    public void setShoppingMallId(Long shoppingMallId) {
        this.shoppingMallId = shoppingMallId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
