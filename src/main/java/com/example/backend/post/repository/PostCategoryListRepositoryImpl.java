package com.example.backend.post.repository;

import com.example.backend.post.dto.PostCategoryListResponse;
import com.example.backend.post.entity.QCategory;
import com.example.backend.post.entity.QPost;
import com.example.backend.post.entity.QPostCategoryRelation;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostCategoryListRepositoryImpl implements PostCategoryListRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  @Override
  public List<PostCategoryListResponse> findPostCategoryList() {

    QCategory category = QCategory.category;
    QPostCategoryRelation relation = QPostCategoryRelation.postCategoryRelation;
    QPost post = QPost.post;

    return queryFactory
      .select(Projections.constructor(
        PostCategoryListResponse.class,
        category.id,
        category.title,
        category.description,
        category.icon,
        post.id.count()
      ))
      .from(category)
      .leftJoin(relation)
        .on(relation.category.eq(category))
      .leftJoin(relation.post, post)
      .groupBy(
        category.id,
        category.title,
        category.description,
        category.icon
      )
      .fetch();
  }
}
