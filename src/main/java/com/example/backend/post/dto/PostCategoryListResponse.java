package com.example.backend.post.dto;

import java.util.UUID;

public record PostCategoryListResponse(
  UUID id,
  String title,
  String description,
  String icon,
  Long postCount
) {
}
