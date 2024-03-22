package com.ssg.wsmt.member.service;

import com.ssg.wsmt.member.dto.CustomUserDetails;
import com.ssg.wsmt.member.entity.UserEntity;
import com.ssg.wsmt.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userData = userRepository.findByUsername(username);

        if (username != null){
            return new CustomUserDetails(userData);
        }
        return null;
    }
}
