package com.example.backend.global.exception.productcategory;

import com.example.backend.global.exception.common.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ProductCategoryParentErrorCode implements ErrorCode {

  PARENT_NOT_FOUND(
    HttpStatus.NOT_FOUND,
    "PRODUCT-CATEGORY-PARENT-001",
    "부모 카테고리가 존재하지 않습니다"
  );

  private final HttpStatus status;

  private final String code;

  private final String message;
}
