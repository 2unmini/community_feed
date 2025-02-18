package com.example.community_feed.user;

import com.example.community_feed.commons.constant.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void signup(UserRequestDto.SignUpRequestDto signUpRequestDto) {
        boolean isExisted = userRepository.existsUserByEmail(signUpRequestDto.getEmail());
        if (isExisted) {
            throw new IllegalArgumentException("회원가입 실패");
        }
        User user = User.builder()
                .email(signUpRequestDto.getEmail())
                .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new IllegalArgumentException("유효한 사용자가 아닙니다"));
        return new CustomUser(user);

    }

    public void login(UserRequestDto.LoginRequestDto loginRequestDto) {

        User user = userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(() -> new IllegalArgumentException("로그인 실패"));
        boolean isMatched = passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword());
        if (!isMatched) {
            throw new IllegalArgumentException("유효한 사용자 정보가 아닙니다");
        }
        log.info("로그인한 사용자 : {}", user.getEmail());

    }
}
