package com.example.community_feed.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostRequestDto {
    @AllArgsConstructor
    @Getter
    public static class CreatePostDto {
        // todo 유효성 추가
        private String title;

        private String text;

        private String imageUrl;
    }

    @AllArgsConstructor
    @Getter
    public static class UpdatePostDto {

        private String title;

        private String text;
    }

}
