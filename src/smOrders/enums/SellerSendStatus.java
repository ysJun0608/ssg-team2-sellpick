package smOrders.enums;

public enum SellerSendStatus {
    PREPARING("배송준비중"),
    COMPLETE("배송완료"),
    CANCEL("주문 취소");

    private String desc;

    SellerSendStatus(String desc) {
        this.desc = desc;
    }
}
