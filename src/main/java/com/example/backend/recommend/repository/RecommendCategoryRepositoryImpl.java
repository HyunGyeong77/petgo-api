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
  public List<RecommendCategoryProductResponse> findProductsByCategoryIds(List<Integer> categoryIds) {

    QRecommendProductCategoryRelation relation = QRecommendProductCategoryRelation.recommendProductCategoryRelation;

    QRecommendProduct product = QRecommendProduct.recommendProduct;

    return queryFactory
      .select(Projections.constructor(
        RecommendCategoryProductResponse.class,
        relation.category.id,
        product.id,
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
