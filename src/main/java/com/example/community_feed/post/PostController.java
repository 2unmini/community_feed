package com.example.community_feed.post;

import com.example.community_feed.post.dto.PostRequestDto;
import com.example.community_feed.post.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponseDto.CreateResponseDto> writePost(@AuthenticationPrincipal UserDetails userDetails, @RequestBody PostRequestDto.CreatePostDto createPostDto) {
        PostResponseDto.CreateResponseDto write = postService.write(userDetails.getUsername(), createPostDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(write);
    }

    @GetMapping
    public ResponseEntity<Page<PostResponseDto.SearchResponseDto>> searchPost(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String title) {
        return ResponseEntity.ok().body(postService.searchPost(page, size, email, title));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto.SearchDetailResponseDto> searchDetail(@PathVariable Long id) {
        return ResponseEntity.ok().body(postService.searchDetailPost(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PostResponseDto.UpdateResponseDto> updatePostDetail(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id, @RequestBody PostRequestDto.UpdatePostDto updatePostDto) {
        PostResponseDto.UpdateResponseDto updateResponseDto = postService.updatePost(userDetails.getUsername(), id, updatePostDto);
        return ResponseEntity.ok().body(updateResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id) {
        postService.deletePost(userDetails.getUsername(), id);
        return ResponseEntity.ok().body("삭제 완료 되었습니다");
    }
}
