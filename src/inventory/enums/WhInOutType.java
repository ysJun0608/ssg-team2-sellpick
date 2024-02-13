package inventory.enums;

public enum WhInOutType {
    INSERT("입고"),
    RELEASE("출고");

    private String desc;

    WhInOutType(String desc) {
        this.desc = desc;
    }
}
