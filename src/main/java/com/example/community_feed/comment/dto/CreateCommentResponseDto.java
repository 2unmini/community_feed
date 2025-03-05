package com.example.community_feed.comment.dto;

import com.example.community_feed.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCommentResponseDto {
    private String text;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static CreateCommentResponseDto toDto(Comment comment) {
        return CreateCommentResponseDto.builder()
                .text(comment.getText())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }
}

