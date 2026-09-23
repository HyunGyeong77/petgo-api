package com.example.backend.post.dto;

public record PostStatisticsResponse(
  Long totalPost,
  Long categoryCount
) {
}
