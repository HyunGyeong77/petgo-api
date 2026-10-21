package com.example.backend.global.exception.post;

import com.example.backend.global.exception.common.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PostCategoryErrorCode implements ErrorCode {

  CATEGORY_NOT_FOUND(
    HttpStatus.NOT_FOUND,
    "POST-CATEGORY-001",
    "해당 카테고리가 존재하지 않습니다"
  );

  private final HttpStatus status;

  private final String code;

  private final String message;
}
