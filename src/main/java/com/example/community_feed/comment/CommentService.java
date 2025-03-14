package com.example.community_feed.comment;

import com.example.community_feed.comment.dto.CreateCommentRequestDto;
import com.example.community_feed.comment.dto.CreateCommentResponseDto;
import com.example.community_feed.comment.dto.ShowCommentResponseDto;
import com.example.community_feed.post.Post;
import com.example.community_feed.post.PostRepository;
import com.example.community_feed.user.User;
import com.example.community_feed.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public CreateCommentResponseDto write(String email, CreateCommentRequestDto requestDto) {
        User user = getUserByEmail(email);
        Post post = postRepository.findById(requestDto.getPostId()).orElseThrow();
        Comment parentComment = findParentComment(requestDto);
        Comment comment = Comment.builder()
                .post(post)
                .user(user)
                .parentCommentId(parentComment)
                .text(requestDto.getText())
                .build();
        Comment savedComment = commentRepository.save(comment);
        return CreateCommentResponseDto.toDto(savedComment);
    }

    private Comment findParentComment(CreateCommentRequestDto requestDto) {
        Long parentCommentId = requestDto.getParentCommentId();
        if (parentCommentId == null) {
            return null;
        }
        return commentRepository.findById(parentCommentId).orElseThrow(() -> new IllegalArgumentException("해당 댓글을 찾을수 없습니다"));
    }

    public List<ShowCommentResponseDto> show(Long postId) {
        List<Comment> comments = commentRepository.findAllByPost_Id(postId);
        return comments.stream().map(ShowCommentResponseDto::toDto).toList();
    }


    public void deleteComment(String email, Long commentId) {
        getUserByEmail(email);
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("해당 댓글을 찾을수 없습니다"));
        commentRepository.delete(comment);

    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }
}
