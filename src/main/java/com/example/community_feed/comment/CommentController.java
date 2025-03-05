package com.example.community_feed.comment;

import com.example.community_feed.comment.dto.CreateCommentRequestDto;
import com.example.community_feed.comment.dto.CreateCommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    // todo 대댓글 작성 구현
    @PostMapping
    public ResponseEntity<CreateCommentResponseDto> writeComment(@AuthenticationPrincipal UserDetails userDetails, @RequestBody CreateCommentRequestDto requestDto) {
        CreateCommentResponseDto responseDto = commentService.write(userDetails.getUsername(), requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

    }
}
