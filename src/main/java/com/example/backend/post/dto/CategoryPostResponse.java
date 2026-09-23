package com.example.backend.post.dto;

import java.util.List;

public record CategoryPostResponse(
  String title,
  String description,
  List<CategoryPostRow> posts
) {
}
