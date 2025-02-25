package com.example.community_feed.post;

import com.example.community_feed.post.dto.PostRequestDto;
import com.example.community_feed.post.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponseDto.CreateResponseDto> writePost(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute PostRequestDto.CreatePostDto createPostDto) {
        PostResponseDto.CreateResponseDto write = postService.write(userDetails.getUsername(), createPostDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(write);
    }
}
