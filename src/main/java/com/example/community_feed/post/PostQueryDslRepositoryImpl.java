package com.example.community_feed.post;

import com.example.community_feed.commons.constant.UserState;
import com.example.community_feed.user.QUser;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class PostQueryDslRepositoryImpl implements PostQueryDslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Post> findAllPost(String email, String title, Pageable pageable) {
        QPost qPost = QPost.post;

        return jpaQueryFactory.selectFrom(qPost)
                .leftJoin(qPost.user, QUser.user).fetchJoin()
                .where(
                        eqUserState(UserState.ACTIVE),
                        eqEmail(email),
                        eqTitle(title)
                )
                .orderBy(qPost.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private BooleanExpression eqTitle(String title) {
        return title != null ? QPost.post.title.eq(title) : null;
    }

    private BooleanExpression eqUserState(UserState userState) {
        return QUser.user.state.eq(userState);
    }

    private BooleanExpression eqEmail(String email) {
        return email != null ? QUser.user.email.eq(email) : null;
    }
}
