package com.example.backend.recommend.dto;

import com.example.backend.recommend.entity.Product;
import lombok.Getter;


public record CategoryProduct(
  int categoryId,
  int productId,
  String name,
  String description,
  String image,
  int price
) {
}
