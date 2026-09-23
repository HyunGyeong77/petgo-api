package com.example.backend.recommend.repository;

import com.example.backend.recommend.dto.RecommendCategoryProductResponse;
import com.example.backend.recommend.entity.*;
import com.querydsl.core.types.Projections;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RecommendCategoryRepositoryImpl implements RecommendCategoryRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  @Override
<<<<<<< Updated upstream:src/main/java/com/example/backend/recommend/repository/CategoryRepositoryImpl.java
  public List<Category> findParents() {

    QCategory category = QCategory.category;

    return queryFactory
      .selectFrom(category)
      .where(category.parent.isNull())
      .fetch();
  }

  @Override
  public List<Category> findChildren(List<Integer> categoryIds) {

    QCategory category = QCategory.category;

    return queryFactory
      .selectFrom(category)
      .where(category.parent.id.in(categoryIds))
      .fetch();
  }

  @Override
  public List<CategoryProduct> findProductsByCategoryIds(List<Integer> categoryIds) {
=======
  public List<RecommendCategoryProductResponse> findProductsByCategoryIds(List<Integer> categoryIds) {
>>>>>>> Stashed changes:src/main/java/com/example/backend/recommend/repository/RecommendCategoryRepositoryImpl.java

    QRecommendProductCategoryRelation relation = QRecommendProductCategoryRelation.recommendProductCategoryRelation;

    QRecommendProduct product = QRecommendProduct.recommendProduct;

    return queryFactory
      .select(Projections.constructor(
        RecommendCategoryProductResponse.class,
        relation.category.id,
        product.productId,
        product.name,
        product.description,
        product.image,
        product.price
      ))
      .from(relation)
      .join(relation.product, product)
      .where(relation.category.id.in(categoryIds))
      .fetch();
  }
}
