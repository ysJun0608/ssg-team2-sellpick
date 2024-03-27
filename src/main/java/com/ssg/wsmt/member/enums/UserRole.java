package com.ssg.wsmt.member.enums;


// 역할은 추후 추가하기 편하게 enum타입으로 관리
public enum UserRole {
    ADMIN("총관리자"),
    WAREHOUSE_MANAGER("창고관리자");

//   예시:  USER("일반 유저");

    private final String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }
    public String getRoleName() {
        return roleName;
    }
}
