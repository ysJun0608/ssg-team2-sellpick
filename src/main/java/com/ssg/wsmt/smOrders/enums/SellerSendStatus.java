package com.ssg.wsmt.smOrders.enums;

public enum SellerSendStatus {
    PREPARING("배송준비중"),
    COMPLETE("배송완료"),
    CANCEL("주문 취소");
    public static SellerSendStatus fromString(String status) {
        for (SellerSendStatus sendStatus : SellerSendStatus.values()) {
            if (sendStatus.name().equalsIgnoreCase(status)) {
                return sendStatus;
            }
        }
        return null; // or throw IllegalArgumentException
    }
    private String desc;

    SellerSendStatus(String desc) {
        this.desc = desc;
    }


    public String getDesc() {
        return desc;
    }
}
