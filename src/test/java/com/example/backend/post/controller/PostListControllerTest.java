package com.example.backend.post.controller;

import com.example.backend.post.dto.CategoryPostListResponse;
import com.example.backend.post.dto.CategoryPostRow;
import com.example.backend.post.service.PostListService;
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

@WebMvcTest(PostListController.class)
public class PostListControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockitoBean
  PostListService postListService;

  @Test
  void 카테고리별_포스트를_조회한다() throws Exception {
    // given
    List<CategoryPostListResponse> postLists = getPostLists();

    when(postListService.getPostList()).thenReturn(postLists);

    // when & then
    mockMvc.perform(
      get("/api/post/category/list")
    )
      .andExpect(status().isOk())
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$.length()").value(1))
      .andExpect(jsonPath("$[0].categoryId").value("00000000-0000-0000-0000-000000000001"))
      .andExpect(jsonPath("$[0].posts").isArray())
      .andExpect(jsonPath("$[0].posts.length()").value(2))
      .andExpect(jsonPath("$[0].posts[0].categoryId").value("00000000-0000-0000-0000-000000000001"))
      .andExpect(jsonPath("$[0].posts[0].postId").value("00000000-0000-0000-0000-000000000001"))
      .andExpect(jsonPath("$[0].posts[1].categoryId").value("00000000-0000-0000-0000-000000000001"))
      .andExpect(jsonPath("$[0].posts[1].postId").value("00000000-0000-0000-0000-000000000002"));
  }

  List<CategoryPostListResponse> getPostLists() {
    UUID uuid1 = UUID.fromString("00000000-0000-0000-0000-000000000001");

    CategoryPostRow post1 = new CategoryPostRow(
      uuid1,
      UUID.fromString("00000000-0000-0000-0000-000000000001"),
      "테스트 제목1",
      "1",
      3
    );

    CategoryPostRow post1_2 = new CategoryPostRow(
      uuid1,
      UUID.fromString("00000000-0000-0000-0000-000000000002"),
      "테스트 제목1-2",
      "2",
      5
    );

    List<CategoryPostRow> posts = List.of(post1, post1_2);

    CategoryPostListResponse postList = new CategoryPostListResponse(
      uuid1,
      posts
    );

    return List.of(postList);
  }
}
