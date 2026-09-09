package com.example.backend.post.repository;

import com.example.backend.post.dto.PostStatisticsResponse;
import com.example.backend.post.entity.QCategory;
import com.example.backend.post.entity.QPost;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostStatisticsRepositoryImpl implements PostStatisticsRepository {

  private final JPAQueryFactory queryFactory;

  @Override
  public PostStatisticsResponse getStatistics() {

    QPost post = QPost.post;

    QCategory category = QCategory.category;

    Long totalPost = queryFactory
      .select(post.count())
      .from(post)
      .fetchOne();

    Long categoryCount = queryFactory
      .select(category.count())
      .from(category)
      .fetchOne();

    return new PostStatisticsResponse(totalPost, categoryCount);
  }
}
