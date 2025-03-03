package com.example.community_feed.post;

import com.example.community_feed.image.ImageService;
import com.example.community_feed.post.dto.PostRequestDto;
import com.example.community_feed.post.dto.PostResponseDto;
import com.example.community_feed.user.User;
import com.example.community_feed.user.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ImageService imageService;
    private final EntityManager entityManager;

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
    public Page<PostResponseDto.SearchResponseDto> searchPost(int page, int size, String email, String title) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return postRepository.findAllPost(email, title, pageable);

    }

    @Transactional(readOnly = true)
    public PostResponseDto.SearchDetailResponseDto searchDetailPost(Long id) {
        Post post = postRepository.findByIdAndUserState(id).orElseThrow(() -> new RuntimeException("유효하지 않은 게시글 입니다"));
        return PostResponseDto.toDetailDto(post);
    }

    @Transactional
    public PostResponseDto.UpdateResponseDto updatePost(String email, Long id, PostRequestDto.UpdatePostDto updatePostDto) {
        Post post = getPost(email, id);
        post.update(updatePostDto);
        entityManager.flush();

        return PostResponseDto.toUpdateDto(post);
    }


    @Transactional
    public void deletePost(String email, Long id) {
        Post post = getPost(email, id);
        if (!post.getImage().isEmpty()) {
            imageService.delete(post.getImage());
        }

        postRepository.delete(post);
    }

    private Post getPost(String email, Long id) {
        return postRepository.findByIdAndUserEmail(email, id).orElseThrow(() -> new RuntimeException("유효하지 않은 게시글 입니다"));
    }
}
