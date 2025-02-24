package com.example.community_feed.post.dto;

import lombok.Builder;
import lombok.Getter;

@Getter

public class PostResponseDto {
    @Getter
    @Builder
    public static class CreateResponseDto {
        private String title;
        private String username;
        private String text;
        private String image;
    }

}
