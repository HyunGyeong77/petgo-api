package com.example.backend.exception.handler;

import com.example.backend.exception.business.BusinessException;
import com.example.backend.exception.common.ErrorCode;
import com.example.backend.exception.common.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ErrorResponse> handleBusinessException(
    BusinessException e
  ) {

    ErrorCode errorCode = e.getErrorCode();

    return ResponseEntity
      .status(HttpStatus.BAD_REQUEST)
      .body(new ErrorResponse(
        errorCode.getCode(),
        errorCode.getMessage()
      ));
  }
}
