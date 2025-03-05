package com.example.community_feed.comment.dto;

import lombok.Getter;

@Getter
public class CreateCommentRequestDto {

    private Long postId;
    private String text;


}
