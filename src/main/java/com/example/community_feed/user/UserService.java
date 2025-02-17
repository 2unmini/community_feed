package com.example.community_feed.user;

import com.example.community_feed.commons.constant.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void signup(UserDto.SignUpRequestDto signUpRequestDto) {
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
        return null;
    }
}
