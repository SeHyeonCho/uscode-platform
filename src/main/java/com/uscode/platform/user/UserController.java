package com.uscode.platform.user;

import com.uscode.platform.email.EmailService;
import com.uscode.platform.user.dto.UserCreateDto;
import com.uscode.platform.user.dto.UserCreateResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final EmailService emailService;

    @PostMapping("/register")
    public UserCreateResponseDto register(@RequestBody UserCreateDto dto) {
        UserCreateResponseDto register = userService.register(dto);
        return register;
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyUser(@RequestParam String token) {
        boolean flag = emailService.verifyToken(token);
        log.info("이메일 인증 로직 실행 [token:{}]", token);
        if(flag) {
            log.info("이메일 인증 로직 성공 [token:{}]", token);
            return ResponseEntity.ok("이메일 인증 완료");

        } else {
            log.info("이메일 인증 로직 실패 [token:{}]", token);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유효하지 않거나 만료된 토큰 입니다.");

        }
    }


}
