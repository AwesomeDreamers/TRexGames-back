package com.trexgames.server.service;

import com.trexgames.server.repository.UserRepository;
import com.trexgames.server.request.SignupDto;
import com.trexgames.server.vo.RoleType;
import com.trexgames.server.vo.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(SignupDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw  new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        Member member = Member.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .username (dto.getUsername())
                .role(RoleType.USER)
                .build();

        userRepository.save(member);



    }

}
