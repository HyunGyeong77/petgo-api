package com.example.backend.post.dto;

import java.util.UUID;

public record CategoryPostRow(
  UUID categoryId,
  UUID postId,
  String title,
  String level,
  Integer readtime
) {
}
