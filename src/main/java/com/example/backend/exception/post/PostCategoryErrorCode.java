package com.example.backend.exception.post;

import com.example.backend.exception.common.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PostCategoryErrorCode implements ErrorCode {

  CATEGORY_NOT_FOUND(
    "POST-CATEGORY-001",
    "해당 카테고리가 존재하지 않습니다"
  );

  private final String code;

  private final String message;
}
