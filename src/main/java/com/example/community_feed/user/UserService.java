package com.example.community_feed.user;

import com.example.community_feed.commons.constant.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void withdraw(String email) {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("유효한 사용자가 아닙니다"));
        user.updateState();

    }
}
