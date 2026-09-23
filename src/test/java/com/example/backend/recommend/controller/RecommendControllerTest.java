package com.example.backend.recommend.controller;

import com.example.backend.recommend.dto.RecommendCategoryResponse;
import com.example.backend.recommend.dto.RecommendParentCategoryResponse;
import com.example.backend.recommend.dto.RecommendProductResponse;
import com.example.backend.recommend.service.RecommendProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RecommendController.class)
public class RecommendControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockitoBean
  RecommendProductService productService;

  @Test
  void 카테고리_전체_조회() throws Exception {

    // given
    List<RecommendParentCategoryResponse> expectedData = getParentResponse();

    when(productService.getCategories()).thenReturn(expectedData);

    // when & then
    mockMvc.perform(
      get("/api/recommend/category/all")
    )
      .andExpect(status().isOk())
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$.length()").value(1))
      .andExpect(jsonPath("$[0].label").value("부모 카테고리"))
      .andExpect(jsonPath("$[0].categories[0].label").value("자식 카테고리"))
      .andExpect(jsonPath("$[0].categories[0].products[0].id").value(1))
      .andExpect(jsonPath("$[0].categories[0].products[0].name").value("테스트 상품"))
      .andExpect(jsonPath("$[0].categories[0].products[0].description").value("테스트 상품 설명"))
      .andExpect(jsonPath("$[0].categories[0].products[0].image").isNotEmpty())
      .andExpect(jsonPath("$[0].categories[0].products[0].price").value(32000));
  }

  private List<RecommendParentCategoryResponse> getParentResponse() {

    RecommendProductResponse product = new RecommendProductResponse(
      1,
      "테스트 상품",
      "테스트 상품 설명",
      "https://test-url/image.jpg",
      32000
    );

    RecommendCategoryResponse category = new RecommendCategoryResponse(
      "자식 카테고리",
      List.of(product)
    );

    RecommendParentCategoryResponse parent = new RecommendParentCategoryResponse(
      "부모 카테고리",
      List.of(category)
    );

    return List.of(parent);
  }
}
