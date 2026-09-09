package com.example.backend.post.controller;

import com.example.backend.post.dto.PostStatisticsResponse;
import com.example.backend.post.service.PostStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostStatisticsController extends BasePostController {

  private final PostStatisticsService postStatisticsService;

  @GetMapping("/statistics")
  public PostStatisticsResponse getStatistics() {
    return postStatisticsService.getStatistics();
  }
}
