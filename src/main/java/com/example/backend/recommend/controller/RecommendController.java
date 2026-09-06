package com.example.backend.recommend.controller;

import com.example.backend.recommend.dto.ParentCategoryResponse;
import com.example.backend.recommend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recommend")
public class RecommendController {

  private final ProductService productService;

  @GetMapping("/category/all")
  public List<ParentCategoryResponse> getCategoryTree() {

    return productService.getCategories();
  }
}
