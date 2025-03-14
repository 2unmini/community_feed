package com.example.community_feed.comment;

import com.example.community_feed.commons.entity.BaseEntity;
import com.example.community_feed.post.Post;
import com.example.community_feed.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    private Comment parentCommentId;

    public Long getPostId() {
        return this.post.getId();
    }

    public String getWriterEmail() {
        return this.user.getEmail();
    }
}
