package com.example.backend.dog.dto;

import java.util.UUID;

public record CheckListResponse(
  UUID id,
  String title
) {
}
