package inventory.enums;

public enum WhType {
    WET("습식창고"),
    DRY("건식창고"),
    BOTH("습건식창고");

    private String desc;

    WhType(String desc) {
        this.desc = desc;
    }
}
