package com.example.community_feed.comment.dto;

import com.example.community_feed.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShowCommentResponseDto {
    private Long commentId;
    private Long parentCommentId;
    private Long postId;
    private String writer;
    private String text;

    public static ShowCommentResponseDto toDto(Comment comment) {
        return ShowCommentResponseDto.builder()
                .commentId(comment.getId())
                .parentCommentId(comment.getParentCommentId() != null ? comment.getParentCommentId().getId() : null)
                .postId(comment.getPostId())
                .writer(comment.getWriterEmail())
                .text(comment.getText())
                .build();

    }
}
