package com.example.backend.recommend.dto;

public record RecommendProductResponse(
  int id,
  String name,
  String description,
  String image,
  int price
) {
}
