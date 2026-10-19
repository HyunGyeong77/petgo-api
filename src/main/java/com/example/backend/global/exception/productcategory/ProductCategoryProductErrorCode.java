package com.example.backend.global.exception.productcategory;

import com.example.backend.global.exception.common.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ProductCategoryProductErrorCode implements ErrorCode {

  PRODUCT_NOT_FOUND(
    HttpStatus.NOT_FOUND,
    "PRODUCT-CATEGORY-PRODUCT-001",
    "상품이 존재하지 않습니다"
  );

  private final HttpStatus status;

  private final String code;

  private final String message;
}
