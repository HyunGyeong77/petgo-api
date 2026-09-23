package com.example.backend.recommend.service;

import com.example.backend.recommend.dto.RecommendCategoryProductResponse;
import com.example.backend.recommend.dto.RecommendCategoryResponse;
import com.example.backend.recommend.dto.RecommendParentCategoryResponse;
import com.example.backend.recommend.dto.RecommendProductResponse;
import com.example.backend.recommend.entity.RecommendCategory;
import com.example.backend.recommend.repository.RecommendCategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecommendProductServiceTest {

  @Mock
  RecommendCategoryRepository categoryRepository;

  @InjectMocks
  RecommendProductService productService;

  @Test
  void 부모_자식_카테고리와_상품을_조회한다() {

    // given
    RecommendCategory parentCategory = new RecommendCategory();
    parentCategory.setId(1);
    parentCategory.setType("부모 카테고리");

    List<RecommendCategory> parents = List.of(parentCategory);
    when(categoryRepository.findByParentIsNull()).thenReturn(parents);

    List<Integer> parentIds = List.of(parentCategory.getId());

    RecommendCategory childCategory = new RecommendCategory();
    childCategory.setId(6);
    childCategory.setType("자식 카테고리");
    childCategory.setParent(parentCategory);

    List<RecommendCategory> children = List.of(childCategory);
    when(categoryRepository.findByParentIdIn(parentIds)).thenReturn(children);

    List<Integer> childIds = List.of(childCategory.getId());

    RecommendCategoryProductResponse categoryProduct = getCategoryProduct(childIds.getFirst());

    List<RecommendCategoryProductResponse> products = List.of(categoryProduct);
    when(categoryRepository.findProductsByCategoryIds(childIds)).thenReturn(products);

    RecommendProductResponse expectedProduct = getProductResponse();

    RecommendCategoryResponse expectedCategory = new RecommendCategoryResponse(
      "자식 카테고리",
      List.of(expectedProduct)
    );

    RecommendParentCategoryResponse expectedParent = new RecommendParentCategoryResponse(
      "부모 카테고리",
      List.of(expectedCategory)
    );

    List<RecommendParentCategoryResponse> expectedParents = List.of(expectedParent);
    
    // when
    List<RecommendParentCategoryResponse> result = productService.getCategories();

    // then
    assertThat(result).usingRecursiveComparison().isEqualTo(expectedParents);
  }

  RecommendCategoryProductResponse getCategoryProduct(int childId) {
    return new RecommendCategoryProductResponse(
      childId,
      1,
      "테스트 상품",
      "테스트 상품 설명",
      "https://test-url/image.jpg",
      32000
    );
  }

  RecommendProductResponse getProductResponse() {
    return new RecommendProductResponse(
      1,
      "테스트 상품",
      "테스트 상품 설명",
      "https://test-url/image.jpg",
      32000
    );
  }
}
