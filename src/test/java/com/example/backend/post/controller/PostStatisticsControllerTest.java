package com.example.backend.post.controller;

import com.example.backend.post.dto.PostStatisticsResponse;
import com.example.backend.post.service.PostStatisticsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostStatisticsController.class)
public class PostStatisticsControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockitoBean
  PostStatisticsService postStatisticsService;

  @Test
  void 게시글_및_카테고리_통계_조회() throws Exception {
    // given
    PostStatisticsResponse statistics = new PostStatisticsResponse(5L, 2L);

    when(postStatisticsService.getStatistics()).thenReturn(statistics);

    // when & then
    mockMvc.perform(
      get("/api/post/statistics")
    )
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.totalPost").value(5L))
      .andExpect(jsonPath("$.categoryCount").value(2L));
  }
}
