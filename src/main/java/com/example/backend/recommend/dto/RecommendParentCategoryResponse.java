package com.example.backend.recommend.dto;

import java.util.List;

public record RecommendParentCategoryResponse(
  String label,
  List<RecommendCategoryResponse> categories
) {
}
