package com.example.community_feed.post;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostQueryDslRepository {

    public List<Post> findAllPost(String email, String title, Pageable pageable);
}
