package com.example.backend.post.controller;

import com.example.backend.post.dto.PostCategoryListResponse;
import com.example.backend.post.service.PostCategoryListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostCategoryListController extends BasePostController {

  private final PostCategoryListService postCategoryListService;

  @GetMapping("/categories")
  public List<PostCategoryListResponse> getCategories() {
    return postCategoryListService.getCategories();
  }
}
