package com.example.backend.post.controller;

import com.example.backend.post.dto.CategoryPostResponse;
import com.example.backend.post.dto.CategoryPostRow;
import com.example.backend.post.service.CategoryPostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryPostController.class)
public class CategoryPostControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockitoBean
  CategoryPostService categoryPostService;

  @Test
  void 카테고리의_게시글을_조회한다() throws Exception {
    // given
    UUID categoryId = UUID.fromString("00000000-0000-0000-0000-000000000001");

    CategoryPostResponse categoryPost = getCategoryPost(categoryId);

    when(categoryPostService.getCategoryPost(categoryId)).thenReturn(categoryPost);

    // when & then
    mockMvc.perform(
      get("/api/post/category/{categoryId}", categoryId)
    )
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.title").value("카테고리 제목"))
      .andExpect(jsonPath("$.description").value("카테고리 설명"))
      .andExpect(jsonPath("$.posts").isArray())
      .andExpect(jsonPath("$.posts[0].categoryId").value(categoryId.toString()))
      .andExpect(jsonPath("$.posts[0].postId").value("00000000-0000-0000-0000-000000000002"))
      .andExpect(jsonPath("$.posts[0].title").value("포스트 제목"))
      .andExpect(jsonPath("$.posts[0].level").value("1"))
      .andExpect(jsonPath("$.posts[0].readtime").value(3));

    verify(categoryPostService).getCategoryPost(categoryId);
  }

  CategoryPostResponse getCategoryPost(UUID categoryId) {

    CategoryPostRow post = new CategoryPostRow(
      categoryId,
      UUID.fromString("00000000-0000-0000-0000-000000000002"),
      "포스트 제목",
      "1",
      3
    );

    return new CategoryPostResponse(
      "카테고리 제목",
      "카테고리 설명",
      List.of(post)
    );
  }
}
