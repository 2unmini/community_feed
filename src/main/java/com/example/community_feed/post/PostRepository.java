package com.example.community_feed.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, PostQueryDslRepository {

    @Query("SELECT p FROM Post p join fetch p.user u where p.id=:id AND u.state='ACTIVE'")
    Optional<Post> findByIdAndUserState(Long id);

    @Query("SELECT p FROM Post p join fetch p.user u where p.id=:id AND u.state='ACTIVE'and u.email=:email")
    Optional<Post> findByIdAndUserEmail(@Param("email") String email, Long id);
}
