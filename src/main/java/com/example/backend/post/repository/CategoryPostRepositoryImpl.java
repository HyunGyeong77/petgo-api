package com.example.backend.post.repository;

import com.example.backend.post.dto.CategoryPostRow;
import com.example.backend.post.dto.PostCategoryRow;
import com.example.backend.post.entity.QCategory;
import com.example.backend.post.entity.QPost;
import com.example.backend.post.entity.QPostCategoryRelation;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CategoryPostRepositoryImpl implements CategoryPostRepository {

  private final JPAQueryFactory queryFactory;

  @Override
  public PostCategoryRow findCategory(UUID categoryId) {

    QCategory category = QCategory.category;

    return queryFactory
      .select(Projections.constructor(
        PostCategoryRow.class,
        category.title,
        category.description
      ))
      .from(category)
      .where(category.id.eq(categoryId))
      .fetchOne();
  }

  @Override
  public List<CategoryPostRow> findPosts(UUID categoryId) {

    QPostCategoryRelation relation = QPostCategoryRelation.postCategoryRelation;

    QPost post =  QPost.post;

    return queryFactory
      .select(Projections.constructor(
        CategoryPostRow.class,
        post.id,
        post.title,
        post.level,
        post.readtime
      ))
      .from(relation)
      .join(relation.post, post)
      .where(relation.category.id.eq(categoryId))
      .fetch();
  }
}
