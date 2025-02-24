package com.example.community_feed.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

public class PostRequestDto {
    @Getter
    @AllArgsConstructor
    public static class CreatePostDto {

        private String title;

        private String text;

        private MultipartFile multipartFile;
    }

}
