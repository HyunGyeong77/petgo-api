package com.example.backend.post.service;

import com.example.backend.post.dto.PostStatisticsResponse;
import com.example.backend.post.repository.PostCategoryRepository;
import com.example.backend.post.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostStatisticsServiceTest {

  @Mock
  PostRepository postRepository;

  @Mock
  PostCategoryRepository postCategoryRepository;

  @InjectMocks
  PostStatisticsService statisticsService;

  @Test
  void 게시글_및_카테고리_통계를_조회한다() {
    // given
    Long postCount = 5L;
    Long categoryCount = 2L;

    when(postRepository.count()).thenReturn(postCount);
    when(postCategoryRepository.count()).thenReturn(categoryCount);

    // when
    PostStatisticsResponse result = statisticsService.getStatistics();

    // then
    assertThat(result)
      .extracting(PostStatisticsResponse::totalPost, PostStatisticsResponse::categoryCount)
      .containsExactly(postCount, categoryCount);
  }
}
