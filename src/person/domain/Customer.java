package person.domain;

import lombok.Data;

@Data
public class Customer {
    // 고객 id
    private Long id;
    // 이름
    private String name;
    // 이메일
    private String email;
    // 비밀번호
    private String password;
    // 전화번호
    private String phone;
    // 주소
    private String address;

    }
