package inventory.enums;

public enum WhSectionType {
    REFRIGERATED("냉장"),
    FROZEN("냉동"),
    DRY("건조"),
    PROCESSED("가공");

    private String desc;

    WhSectionType(String desc) {
        this.desc = desc;
    }
}
