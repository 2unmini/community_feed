package com.example.community_feed.post;

import com.example.community_feed.commons.entity.BaseEntity;
import com.example.community_feed.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String text;

    @Column(length = 150)
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;


}
