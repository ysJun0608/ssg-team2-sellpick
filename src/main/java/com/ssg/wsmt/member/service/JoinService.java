package com.ssg.wsmt.member.service;

import com.ssg.wsmt.member.dto.UserDTO;
import com.ssg.wsmt.member.entity.UserEntity;
import com.ssg.wsmt.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class JoinService {

    @Autowired
    public final UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void joinProcess(UserDTO userDTO){

        boolean ExistUser = userRepository.existsByUsername(userDTO.getUsername());
        if(ExistUser){
            return;
        }
        UserEntity data = new UserEntity();
        data.setUsername(userDTO.getUsername());
        data.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        data.setRole("ROLE_USER");

        userRepository.save(data);
    }
}
