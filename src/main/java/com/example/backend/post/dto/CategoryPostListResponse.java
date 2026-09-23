package com.example.backend.post.dto;

import java.util.List;
import java.util.UUID;

public record CategoryPostListResponse(
  UUID categoryId,
  List<CategoryPostRow> posts
) {
}
