package person.domain;

import lombok.Data;

@Data
public class BusinessOwner {
    // 사업자 id
    private Long id;
    // 사업자 이름
    private String name;
    // 이메일
    private String email;
    // 비밀번호
    private String password;
    // 핸드폰
    private String phone;
    // 주소
    private String address;

    // TODO : 사업자 번호


    }

