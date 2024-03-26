package com.ssg.wsmt.member.enums;

public enum UserRole {
    ADMIN("총관리자"),
    WAREHOUSE_MANAGER("창고관리자"),

    USER("일반 유저");

    private final String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }
    public String getRoleName() {
        return roleName;
    }
}
