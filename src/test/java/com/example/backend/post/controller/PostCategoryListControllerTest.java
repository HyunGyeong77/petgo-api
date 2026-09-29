package com.example.backend.post.controller;

import com.example.backend.post.dto.PostCategoryListResponse;
import com.example.backend.post.service.PostCategoryListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostCategoryListController.class)
public class PostCategoryListControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockitoBean
  PostCategoryListService categoryService;

  @Test
  void 포스트_카테고리_조회() throws Exception {
    // given
    List<PostCategoryListResponse> categories = getCategories();

    when(categoryService.getCategories()).thenReturn(categories);

    // when & then
    mockMvc.perform(
      get("/api/post/categories")
    )
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.length()").value(1))
      .andExpect(jsonPath("$[0].id").value("00000000-0000-0000-0000-000000000001"))
      .andExpect(jsonPath("$[0].title").value("테스트 제목"))
      .andExpect(jsonPath("$[0].description").value("테스트 설명"))
      .andExpect(jsonPath("$[0].icon").value("테스트 아이콘"))
      .andExpect(jsonPath("$[0].postCount").value(5));
  }

  List<PostCategoryListResponse> getCategories() {

    UUID id = UUID.fromString("00000000-0000-0000-0000-000000000001");

    PostCategoryListResponse category = new PostCategoryListResponse(
      id,
      "테스트 제목",
      "테스트 설명",
      "테스트 아이콘",
      5L
    );

    return List.of(category);
  }
}
