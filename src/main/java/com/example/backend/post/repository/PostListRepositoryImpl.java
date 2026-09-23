package com.example.backend.post.repository;

import com.example.backend.post.dto.CategoryPostRow;
import com.example.backend.post.entity.*;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostListRepositoryImpl implements PostListRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  @Override
  public List<CategoryPostRow> findPosts() {

    QPostCategoryRelation relation = QPostCategoryRelation.postCategoryRelation;

    QPost post = QPost.post;

    return queryFactory
      .select(Projections.constructor(
        CategoryPostRow.class,
        relation.category.id,
        post.id,
        post.title,
        post.level,
        post.readtime
      ))
      .from(relation)
      .join(relation.post, post)
      .where(post.preview.isTrue())
      .orderBy(post.createdAt.asc())
      .fetch();
  }
}
