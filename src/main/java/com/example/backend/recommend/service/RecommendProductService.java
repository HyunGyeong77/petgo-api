package com.example.backend.recommend.service;

import com.example.backend.exception.business.BusinessException;
import com.example.backend.exception.productcategory.ProductCategoryChildErrorCode;
import com.example.backend.exception.productcategory.ProductCategoryParentErrorCode;
import com.example.backend.exception.productcategory.ProductCategoryProductErrorCode;
import com.example.backend.recommend.dto.RecommendCategoryProductResponse;
import com.example.backend.recommend.dto.RecommendCategoryResponse;
import com.example.backend.recommend.dto.RecommendParentCategoryResponse;
import com.example.backend.recommend.dto.RecommendProductResponse;
import com.example.backend.recommend.entity.RecommendCategory;
import com.example.backend.recommend.repository.RecommendCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendProductService {

  private final RecommendCategoryRepository categoryRepository;

  public List<RecommendParentCategoryResponse> getCategories() {

    // 1. 부모 카테고리 조회
    List<RecommendCategory> parents = categoryRepository.findByParentIsNull();

    if (parents.isEmpty()) {
      throw new BusinessException(
        ProductCategoryParentErrorCode.PARENT_NOT_FOUND
      );
    }

    // 2. 부모 카테고리 ID 추출
    List<Integer> parentIds = parents.stream().map(RecommendCategory::getId).toList();

    // 3. 자식 카테고리 조회
    List<RecommendCategory> children = categoryRepository.findByParentIdIn(parentIds);

    if (children.isEmpty()) {
      throw new BusinessException(
        ProductCategoryChildErrorCode.CHILD_NOT_FOUND
      );
    }

    // 4. 자식 카테고리 ID 추출
    List<Integer> categoryIds = children.stream().map(RecommendCategory::getId).toList();

    // 5. 상품 조회
    List<RecommendCategoryProductResponse> products = categoryRepository.findProductsByCategoryIds(categoryIds);

    if (products.isEmpty()) {
      throw new BusinessException(
        ProductCategoryProductErrorCode.PRODUCT_NOT_FOUND
      );
    }

    // 6. 부모 ID 기준으로 자식 카테고리 그룹화
    Map<Integer, List<RecommendCategory>> childrenByParent = children.stream().collect(
      Collectors.groupingBy(child -> child.getParent().getId())
    );

    // 7. 카테고리 ID 기준으로 상품 그룹화
    Map<Integer, List<RecommendCategoryProductResponse>> productsByCategory = products.stream().collect(
      Collectors.groupingBy(RecommendCategoryProductResponse::categoryId)
    );

    // 8. 부모 → 자식 → 상품 구조 생성
    return parents.stream().map(parent -> {

      List<RecommendCategoryResponse> childResponses =
        childrenByParent.getOrDefault(parent.getId(), List.of())
          .stream().map(child -> {

            List<RecommendProductResponse> productResponses =
              productsByCategory.getOrDefault(child.getId(), List.of())
                .stream().map(product -> new RecommendProductResponse(
                  product.productId(),
                  product.name(),
                  product.description(),
                  product.image(),
                  product.price()
                )).toList();

            return new RecommendCategoryResponse(
              child.getType(),
              productResponses
            );
          }).toList();

      return new RecommendParentCategoryResponse(
        parent.getType(),
        childResponses
      );
    }).toList();
  }
}
