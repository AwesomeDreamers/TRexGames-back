package com.trexgames.server.service;

import com.trexgames.server.entity.Mail;
import com.trexgames.server.repository.RedisRepository;
import com.trexgames.server.repository.MemberRepository;
import com.trexgames.server.request.EmailVerifyDto;
import com.trexgames.server.request.SignupDto;
import com.trexgames.server.entity.RoleType;
import com.trexgames.server.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;
    private final RedisRepository redisRepository;
    
    // 이메일 중복 확인 및 이메일 전송
    public void validateMember (String email) {
        // 이메일 중복 확인
        if (memberRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }
        // 이메일 전송 및 redis 저장함
        Mail mail = Mail.create(email);
        redisRepository.save(mail);

        sendEmail(
                email,
                "trexgames의 인증번호 이메일",
                "인증번호: " + mail.getCode()
        );
    }

    // 이메일 코드 인증
    public void codeVerify (EmailVerifyDto dto) throws Exception {
        Optional<Mail> optionalMail = redisRepository.findById(dto.getEmail());

        if (optionalMail.isEmpty()) {
            throw new IllegalStateException();
        }
        Mail mail = optionalMail.get();

        if (!String.valueOf(mail.getCode()).equals(dto.getCode())) {
            throw new Exception();
        }
    }

    // 회원 가입
    public void signup (SignupDto dto) {
        Optional<Mail> mail = redisRepository.findById(dto.getEmail());
        if (mail.isEmpty()) {
            throw new IllegalStateException();
        } else {
            Member member = Member.builder()
                    .email(dto.getEmail())
                    .password(passwordEncoder.encode(dto.getPassword()))
                    .username(dto.getUsername())
                    .role(RoleType.USER)
                    .build();
            memberRepository.save(member);
        }
    }

    private void sendEmail(String toEmail, String title, String content) {
        SimpleMailMessage simpleMailMessage = createEmailMessage(toEmail, title, content);
        try {
            mailSender.send(simpleMailMessage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private SimpleMailMessage createEmailMessage(final String toEmail, final String title, final String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setSubject(title);
        simpleMailMessage.setText(content);
        return simpleMailMessage;
    }


}
