package com.example.backend.recommend.dto;


public record RecommendCategoryProductResponse(
  int categoryId,
  int productId,
  String name,
  String description,
  String image,
  int price
) {
}
