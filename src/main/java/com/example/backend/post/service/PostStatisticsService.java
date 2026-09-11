package com.example.backend.post.service;

import com.example.backend.post.dto.PostStatisticsResponse;
import com.example.backend.post.repository.PostCategoryRepository;
import com.example.backend.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostStatisticsService {

  private final PostRepository postRepository;
  private final PostCategoryRepository postCategoryRepository;

  public PostStatisticsResponse getStatistics() {
    long totalPost = postRepository.count();
    long categoryCount = postCategoryRepository.count();

    return new PostStatisticsResponse(totalPost, categoryCount);
  }
}
