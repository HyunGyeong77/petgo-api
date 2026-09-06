package com.example.backend.recommend.repository;

import com.example.backend.recommend.dto.CategoryProduct;
import com.example.backend.recommend.entity.*;
import com.querydsl.core.types.Projections;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  @Override
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

    QProductCategoryRelation relation = QProductCategoryRelation.productCategoryRelation;

    QProduct product = QProduct.product;

    return queryFactory
      .select(Projections.constructor(
        CategoryProduct.class,
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
