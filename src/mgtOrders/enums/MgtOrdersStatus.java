package mgtOrders.enums;

public enum MgtOrdersStatus {
    READY("발주 요청"),
    DONE("발주 확정"),
    DELIVERED("배송 완료"),
    CANCEL("발주 취소"),
    RETURN("반품");

    private String desc;

    MgtOrdersStatus(String desc) {
        this.desc = desc;
    }
}
