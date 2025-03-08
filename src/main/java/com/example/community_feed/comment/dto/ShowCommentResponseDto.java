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
    private Long postId;
    private String writer;
    private String text;

    public static ShowCommentResponseDto toDto(Comment comment){
        return ShowCommentResponseDto.builder()
                .postId(comment.getPostId())
                .writer(comment.getWriterEmail())
                .text(comment.getText())
                .build();

    }
}
