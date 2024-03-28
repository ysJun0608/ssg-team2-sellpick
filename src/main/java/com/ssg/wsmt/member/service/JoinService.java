package com.ssg.wsmt.member.service;

import com.ssg.wsmt.member.dto.UserDTO;
import com.ssg.wsmt.member.entity.UserEntity;
import com.ssg.wsmt.member.enums.UserRole;
import com.ssg.wsmt.member.exception.DuplicateUserException;
import com.ssg.wsmt.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class JoinService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    //회원 가입 로직,회원 가입 폼에서 입력하는거 그대로 입력됨 role은 현재 수동으로 입력해야함
    public void joinProcess(UserDTO userDTO) {

        boolean ExistUser = userRepository.existsByEmail(userDTO.getEmail());
        if (ExistUser) {
            throw new DuplicateUserException("User with email " + userDTO.getEmail() + " already exists.");
        }

        String password = passwordEncoder.encode(userDTO.getPassword());

        UserEntity userEntity = UserEntity.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(password)
                .role(UserRole.WAREHOUSE_MANAGER)
                .phone(userDTO.getPhone())
                .zipcode(userDTO.getZipcode())
                .streetAddress(userDTO.getStreetAddress())
                .detailAddress(userDTO.getDetailAddress())
                .build();

        userRepository.save(userEntity);

        log.info(userEntity);
    }
}
