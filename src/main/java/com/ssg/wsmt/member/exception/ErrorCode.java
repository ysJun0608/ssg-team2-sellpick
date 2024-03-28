package com.ssg.wsmt.member.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


//예외 코드 모음
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorCode {

    ID_OR_PASSWORD_FAIL("700"), //ID나 패스워드가 틀렸습니다
    USER_NOT_FOUND("701"), //해당 유저를 찾지 못했습니다
    USER_NOT_AUTHORIZED("702"), //인증되지 않은 유저입니다
    USER_NOT_AUTHENTICATE("703"), //권한이 없는 유저입니다
    USER_NOT_FOUND_BY_SYSTEM_ERROR("506"), // 시스템 오류로 인해 유저를 찾지 못했습니다
    UNKNOWN_ERROR("666"); //알 수 없는 오류

    private String description;

}
