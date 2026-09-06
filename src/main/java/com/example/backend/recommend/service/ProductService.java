package com.example.backend.recommend.service;

import com.example.backend.recommend.dto.CategoryProduct;
import com.example.backend.recommend.dto.CategoryResponse;
import com.example.backend.recommend.dto.ParentCategoryResponse;
import com.example.backend.recommend.dto.ProductResponse;
import com.example.backend.recommend.entity.Category;
import com.example.backend.recommend.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final CategoryRepository categoryRepository;

  public List<ParentCategoryResponse> getCategories() {

    // 1. 부모 카테고리 조회
    List<Category> parents = categoryRepository.findParents();

    if (parents.isEmpty()) return List.of();

    // 2. 부모 카테고리 ID 추출
    List<Integer> parentIds = parents.stream().map(Category::getId).toList();

    // 3. 자식 카테고리 조회
    List<Category> children = categoryRepository.findChildren(parentIds);

    if (children.isEmpty()) {
      return parents.stream().map(parent -> new ParentCategoryResponse(
          parent.getType(),
          List.of()
        )).toList();
    }

    // 4. 자식 카테고리 ID 추출
    List<Integer> categoryIds = children.stream().map(Category::getId).toList();

    // 5. 상품 조회
    List<CategoryProduct> products = categoryRepository.findProductsByCategoryIds(categoryIds);

    // 6. 부모 ID 기준으로 자식 카테고리 그룹화
    Map<Integer, List<Category>> childrenByParent = children.stream().collect(
      Collectors.groupingBy(child -> child.getParent().getId())
    );

    // 7. 카테고리 ID 기준으로 상품 그룹화
    Map<Integer, List<CategoryProduct>> productsByCategory = products.stream().collect(
      Collectors.groupingBy(CategoryProduct::categoryId)
    );

    // 8. 부모 → 자식 → 상품 구조 생성
    return parents.stream().map(parent -> {

      List<CategoryResponse> childResponses =
        childrenByParent.getOrDefault(parent.getId(), List.of())
          .stream().map(child -> {

            List<ProductResponse> productResponses =
              productsByCategory.getOrDefault(child.getId(), List.of())
                .stream().map(product -> new ProductResponse(
                  product.productId(),
                  product.name(),
                  product.description(),
                  product.image(),
                  product.price()
                )).toList();

            return new CategoryResponse(
              child.getType(),
              productResponses
            );
          }).toList();

      return new ParentCategoryResponse(
        parent.getType(),
        childResponses
      );
    }).toList();
  }
}
