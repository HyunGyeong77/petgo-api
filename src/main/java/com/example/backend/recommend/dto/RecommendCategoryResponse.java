package com.example.backend.recommend.dto;

import java.util.List;

public record RecommendCategoryResponse(
  String label,
  List<RecommendProductResponse> products
) {
}
