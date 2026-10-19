package com.example.backend.global.exception.productcategory;

import com.example.backend.global.exception.common.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ProductCategoryChildErrorCode implements ErrorCode {

  CHILD_NOT_FOUND(
    HttpStatus.NOT_FOUND,
    "PRODUCT-CATEGORY-CHILD-001",
    "자식 카테고리가 존재하지 않습니다"
  );

  private final HttpStatus status;

  private final String code;

  private final String message;
}
