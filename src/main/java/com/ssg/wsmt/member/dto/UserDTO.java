package com.ssg.wsmt.member.dto;


import com.ssg.wsmt.member.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {
    private String username;
    private String email;
    private String password;
    private UserRole role;
    private String phone;
    private String zipcode;
    private String streetAddress;
    private String detailAddress;

}
