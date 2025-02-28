package com.example.community_feed.post.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

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

    @Getter
    @Builder
    public static class SearchResponseDto {
        private String title;
        private String username;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Getter
    @Builder
    public static class SearchDetailResponseDto {
        private String title;
        private String username;
        private String text;
        private String image;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

}
