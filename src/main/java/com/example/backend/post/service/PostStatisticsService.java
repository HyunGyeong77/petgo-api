package com.example.backend.post.service;

import com.example.backend.post.dto.PostStatisticsResponse;
import com.example.backend.post.repository.PostStatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostStatisticsService {

  private final PostStatisticsRepository postStatisticsRepository;

  public PostStatisticsResponse getStatistics() {
    return postStatisticsRepository.getStatistics();
  }
}
