package com.example.backend.exception.common;

public record ErrorResponse(
  String code,
  String message
) {

}
