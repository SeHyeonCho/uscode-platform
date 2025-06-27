package com.uscode.platform.user;

import com.uscode.platform.auth.JwtTokenProvider;
import com.uscode.platform.email.EmailService;
import com.uscode.platform.user.dto.UserCreateDto;
import com.uscode.platform.user.dto.UserCreateResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public UserCreateResponseDto register(UserCreateDto dto) {
        if (isDuplicateEmail(dto.getEmail())) {
            throw new RuntimeException("이미 존재하는 이메일 입니다.");
        }
        String encodePassword = passwordEncoder.encode(dto.getPassword());
        User user = userRepository.save(User.of(dto.getName(), dto.getEmail(), encodePassword));
        log.info("USER 생성 완료 [userId = {}]", user.getId());

        String token = UUID.randomUUID().toString();
        emailService.saveToken(token, dto.getEmail());
        emailService.sendVerificationMail(dto.getEmail(), token);
        return new UserCreateResponseDto(dto.getName(), dto.getEmail());
    }

    public boolean isDuplicateEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
