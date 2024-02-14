package inventory.enums;

import lombok.Data;


public enum WhInOutType {
    INSERT_REQUEST("입고대기"),
    INSERT_CANCEL("입고취소"),
    INSERT_CONFIRM("입고확인"),

    RELEASE_REQUEST("출고대기"),
    RELEASE_CANCEL("출고취소"),
    RELEASE_CONFIRM("출고확인");

    private String desc;

    WhInOutType(String desc) {
        this.desc = desc;
    }
}
