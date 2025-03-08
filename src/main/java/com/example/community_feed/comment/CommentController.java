package com.example.community_feed.comment;

import com.example.community_feed.comment.dto.CreateCommentRequestDto;
import com.example.community_feed.comment.dto.CreateCommentResponseDto;
import com.example.community_feed.comment.dto.ShowCommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CreateCommentResponseDto> writeComment(@AuthenticationPrincipal UserDetails userDetails, @RequestBody CreateCommentRequestDto requestDto) {
        CreateCommentResponseDto responseDto = commentService.write(userDetails.getUsername(), requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

    }

    @GetMapping()
    public ResponseEntity<List<ShowCommentResponseDto>> showComment(@RequestParam Long postId) {
        List<ShowCommentResponseDto> responseDtos = commentService.show(postId);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDtos);

    }
}
