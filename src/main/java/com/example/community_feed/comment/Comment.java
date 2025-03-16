package com.example.community_feed.comment;

import com.example.community_feed.commons.constant.CommentState;
import com.example.community_feed.commons.entity.BaseEntity;
import com.example.community_feed.post.Post;
import com.example.community_feed.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@DynamicInsert
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

    @Column(columnDefinition = "varchar(50) default 'EXISTING'")
    @Enumerated(EnumType.STRING)
    private CommentState commentState;

    public Long getPostId() {
        return this.post.getId();
    }

    public String getWriterEmail() {
        return this.user.getEmail();
    }

    public void delete() {
        this.commentState = CommentState.DELETED;
    }
}
