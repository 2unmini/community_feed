package com.example.community_feed.post;

import com.example.community_feed.post.dto.PostResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostQueryDslRepository {

    public Page<PostResponseDto.SearchResponseDto> findAllPost(String email, String title, Pageable pageable);
}
