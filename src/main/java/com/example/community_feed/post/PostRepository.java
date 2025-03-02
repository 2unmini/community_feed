package com.example.community_feed.post;

import com.example.community_feed.commons.constant.UserState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, PostQueryDslRepository {
    @Query("SELECT p FROM Post p join fetch p.user u where  u.state=:state")
    Page<Post> findAllPost(@Param("state") UserState userState, Pageable pageable); // 피드백 받고 지울예정

    @Query("SELECT p FROM Post p join fetch p.user u where p.id=:id AND u.state=:state")
    Optional<Post> findByIdAndUserState(Long id, @Param("state") UserState userState);
}
