package com.example.backend.post.repository;

import com.example.backend.post.dto.PostStatisticsResponse;

public interface PostStatisticsRepository {

  PostStatisticsResponse getStatistics();
}
