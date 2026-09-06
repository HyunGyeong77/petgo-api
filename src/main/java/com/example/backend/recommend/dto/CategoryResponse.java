package com.example.backend.recommend.dto;

import java.util.List;

public record CategoryResponse(
  String label,
  List<ProductResponse> products
) {
}
