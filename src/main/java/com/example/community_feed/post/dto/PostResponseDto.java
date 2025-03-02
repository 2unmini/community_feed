package com.example.community_feed.post.dto;

import com.example.community_feed.post.Post;
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

    public static PostResponseDto.SearchResponseDto toDto(Post post) {
        return PostResponseDto.SearchResponseDto.builder()
                .title(post.getTitle())
                .username(post.getUser().getEmail())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }

    public static PostResponseDto.SearchDetailResponseDto toDetailDto(Post post) {
        return PostResponseDto.SearchDetailResponseDto.builder()
                .title(post.getTitle())
                .username(post.getUser().getEmail())
                .text(post.getText())
                .image(post.getImage())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }

}
