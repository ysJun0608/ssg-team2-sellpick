package com.ssg.wsmt.inventory.enums;

public enum WhInOutType {
    INSERT_REQUEST("입고 대기"),
    INSERT_CONFIRM("입고 확정"),
    INSERT_CANCEL("입고 취소"),
    RELEASE_REQUEST("출고 대기"),
    RELEASE_CONFIRM("출고 확정"),
    RELEASE_CANCEL("출고 취소");

    private String desc;

    WhInOutType(String desc) {
        this.desc = desc;
    }
}
