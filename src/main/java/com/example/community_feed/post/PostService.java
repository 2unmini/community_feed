package com.example.community_feed.post;

import com.example.community_feed.post.dto.PostRequestDto;
import com.example.community_feed.post.dto.PostResponseDto;
import com.example.community_feed.user.User;
import com.example.community_feed.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostResponseDto.CreateResponseDto write(String username, PostRequestDto.CreatePostDto createPostDto) {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new IllegalArgumentException("사용자가 아닙니다"));
        Post post = Post.builder().image(null)
                .title(createPostDto.getTitle())
                .text(createPostDto.getText())
                .user(user)
                .build();

        postRepository.save(post);
        return PostResponseDto.CreateResponseDto.builder()
                .title(post.getTitle())
                .text(post.getText())
                .username(username)
                .image(post.getImage())
                .build();
    }
}
