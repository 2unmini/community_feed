package com.example.community_feed.post;

import com.example.community_feed.post.dto.PostRequestDto;
import com.example.community_feed.post.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<PostResponseDto.SearchResponseDto>> searchPost(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        return ResponseEntity.ok().body(postService.searchPost(page, size));
    }
}
