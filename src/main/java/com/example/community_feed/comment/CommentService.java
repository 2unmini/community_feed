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
        User user = userRepository.findByEmail(email).orElseThrow();
        Post post = postRepository.findById(requestDto.getPostId()).orElseThrow();
        Comment comment = Comment.builder()
                .post(post)
                .user(user)
                .text(requestDto.getText())
                .build();
        Comment savedComment = commentRepository.save(comment);
        return CreateCommentResponseDto.toDto(savedComment);
    }

    public List<ShowCommentResponseDto> show(Long postId) {
        List<Comment> comments = commentRepository.findAllByPostId(postId);
        return comments.stream().map(ShowCommentResponseDto::toDto).toList();
    }
}
