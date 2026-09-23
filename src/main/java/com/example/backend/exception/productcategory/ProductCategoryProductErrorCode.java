package com.example.backend.exception.productcategory;

import com.example.backend.exception.common.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductCategoryProductErrorCode implements ErrorCode {

  PRODUCT_NOT_FOUND(
    "PRODUCT-CATEGORY-PRODUCT-001",
    "상품이 존재하지 않습니다"
  );

  private final String code;

  private final String message;
}
