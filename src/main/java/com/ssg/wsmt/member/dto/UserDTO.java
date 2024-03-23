package com.ssg.wsmt.member.dto;


import com.ssg.wsmt.member.enums.UserRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
public class UserDTO {
    private String username;
    private String email;
    private UserRole role;
    private String phone;
    private String address;
}
