package com.ssg.wsmt.member.service;

import com.ssg.wsmt.member.dto.UserDTO;
import com.ssg.wsmt.member.entity.UserEntity;
import com.ssg.wsmt.member.enums.UserRole;
import com.ssg.wsmt.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class JoinService {

    private final UserRepository userRepository;

    //비밀번호 암호화
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    //회원 가입 로직,회원 가입 폼에서 입력하는거 그대로 입력됨 role은 현재 수동으로 입력해야함
    public void joinProcess(UserDTO userDTO){

        boolean ExistUser = userRepository.existsByUsername(userDTO.getUsername());
        if(ExistUser){
            return;
        }

        String password = passwordEncoder.encode(userDTO.getPassword());

        UserEntity userEntity = UserEntity.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(password)
                .role(UserRole.ADMIN)
                .phone(userDTO.getPhone())
                .address(userDTO.getAddress())
                .build();

        userRepository.save(userEntity);

        log.info(userEntity);
    }
}
