package com.example.community_feed.comment.dto;

import lombok.Getter;

@Getter
public class CreateCommentRequestDto {

    private Long postId;
    private Long parentCommentId;
    private String text;


}
