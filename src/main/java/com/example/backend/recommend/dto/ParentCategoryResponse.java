package com.example.backend.recommend.dto;

import java.util.List;

public record ParentCategoryResponse(
  String label,
  List<CategoryResponse> categories
) {
}
