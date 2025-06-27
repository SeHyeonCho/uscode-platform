package com.uscode.platform.email;

import com.uscode.platform.user.User;
import com.uscode.platform.user.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final RedisTemplate<String, String> redisTemplate;
    private final UserRepository userRepository;

    public void saveToken(String token, String email) {
        redisTemplate.opsForValue().set("email:verify:" + token, email, Duration.ofMinutes(10));
    }

    public Optional<String> getEmailByToken(String token) {
        return Optional.of(redisTemplate.opsForValue().get("email:verify" + token));
    }

    public void removeToken(String token) {
        redisTemplate.delete("email:verify:" + token);
    }

    public boolean verifyToken(String token) {
        String key = "email:verify:" + token;
        String email = redisTemplate.opsForValue().get(key);

        if (email == null) {
            return false; // 유효하지 않거나 만료된 토큰
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("유저 없음"));

        user.validateUser(); // isVerified 필드 설정
        userRepository.save(user);

        redisTemplate.delete(key); // 재사용 방지
        return true;
    }

    public void sendVerificationMail(String to, String token) {
        String subject = "이메일 인증 링크";
        String url = "http://localhost:8080/api/users/verify?token=" + token;
        String content = "<h3>이메일 인증</h3>" +
                "<p>아래 링크를 클릭하여 이메일 인증을 완료해주세요:</p>" +
                "<a href=\"" + url + "\">이메일 인증하기</a>";

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("이메일 전송 실패", e);
        }
    }
}
