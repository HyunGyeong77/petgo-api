package com.example.backend.post.dto;

import java.util.UUID;

public record CategoryPostRow(
  UUID id,
  String title,
  String level,
  Integer readtime
) {
}
