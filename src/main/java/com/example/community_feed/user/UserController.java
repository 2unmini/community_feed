package com.example.community_feed.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody UserRequestDto.SignUpRequestDto signUpRequestDto) {
        userService.signup(signUpRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 성공");
    }
}
