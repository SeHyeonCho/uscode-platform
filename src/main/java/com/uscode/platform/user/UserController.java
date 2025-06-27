package com.uscode.platform.user;

import com.uscode.platform.auth.JwtTokenProvider;
import com.uscode.platform.email.EmailService;
import com.uscode.platform.user.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public UserCreateResponseDto register(@RequestBody UserCreateDto dto) {
        UserCreateResponseDto register = userService.register(dto);
        return register;
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyUser(@RequestParam String token) {
        boolean flag = emailService.verifyToken(token);
        log.info("이메일 인증 로직 실행 [token:{}]", token);
        if (flag) {
            log.info("이메일 인증 로직 성공 [token:{}]", token);
            return ResponseEntity.ok("이메일 인증 완료");

        } else {
            log.info("이메일 인증 로직 실패 [token:{}]", token);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유효하지 않거나 만료된 토큰 입니다.");

        }
    }

    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody UserLoginDto dto) {
        User user = userService.findByEmail(dto.getEmail()).orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("비밀번호 불일치");
        }

        String accessToken = jwtTokenProvider.createAccessToken(user.getId(), user.getRole());
        return new UserLoginResponseDto(accessToken, user.getRole().name(), user.getName());
    }

    @GetMapping("/{userId}")
    public UserDetailDto getUserInfo(@PathVariable Long userId) {
        User user = userService.findById(userId);
        return new UserDetailDto(user);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<String> updateUserInfo(@PathVariable Long userId, @RequestBody UserDetailDto dto) {
        User user = userService.findById(userId);
        user.updateInfo(dto.getName(), dto.getNumber(), dto.getAddress());
        return ResponseEntity.ok("유저 정보 수정 완료");
    }

}
