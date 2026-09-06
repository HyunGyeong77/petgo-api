package com.example.backend.recommend.dto;

public record ProductResponse (
  int id,
  String name,
  String description,
  String image,
  int price
) {
}
