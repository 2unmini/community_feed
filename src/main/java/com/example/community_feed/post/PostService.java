package com.example.community_feed.post;

import com.example.community_feed.post.dto.PostRequestDto;
import com.example.community_feed.post.dto.PostResponseDto;
import com.example.community_feed.user.User;
import com.example.community_feed.user.UserRepository;
import com.example.community_feed.util.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final S3Uploader s3Uploader;

    public PostResponseDto.CreateResponseDto write(String username, PostRequestDto.CreatePostDto createPostDto) {
        String image = null;
        try {
            if (createPostDto.getImage() != null) {
                image = s3Uploader.upload(createPostDto.getImage());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        User user = userRepository.findByEmail(username).orElseThrow(() -> new IllegalArgumentException("사용자가 아닙니다"));
        Post post = Post.builder()
                .image(image)
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
