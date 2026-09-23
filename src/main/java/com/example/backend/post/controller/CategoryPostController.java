package com.example.backend.post.controller;

import com.example.backend.post.dto.CategoryPostResponse;
import com.example.backend.post.service.CategoryPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CategoryPostController extends BasePostController {

  private final CategoryPostService categoryPostService;

  @GetMapping("/category/{categoryId}")
  public CategoryPostResponse getCategoryPost(@PathVariable UUID categoryId) {
    return categoryPostService.getCategoryPost(categoryId);
  }
}
