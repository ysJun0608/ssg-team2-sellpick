package com.ssg.wsmt.member.exception;

public class DuplicateUserException extends RuntimeException {
    public DuplicateUserException(String message){
        super(message);
    }
}
