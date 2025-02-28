package com.example.community_feed.post;

import com.example.community_feed.post.dto.PostRequestDto;
import com.example.community_feed.post.dto.PostResponseDto;
import com.example.community_feed.user.User;
import com.example.community_feed.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.community_feed.commons.constant.UserState.ACTIVE;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostResponseDto.CreateResponseDto write(String username, PostRequestDto.CreatePostDto createPostDto) {

        User user = userRepository.findByEmail(username).orElseThrow(() -> new IllegalArgumentException("사용자가 아닙니다"));
        Post post = Post.builder()
                .image(createPostDto.getImageUrl())
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

    @Transactional(readOnly = true)
    public List<PostResponseDto.SearchResponseDto> searchPost(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("createdAt").descending());
        Page<Post> allPost = postRepository.findAllPost(ACTIVE, pageable);
        return allPost.stream().map(Post::toDto).toList();

    }
}
