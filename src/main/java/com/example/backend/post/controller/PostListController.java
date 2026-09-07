package com.example.backend.post.controller;

import com.example.backend.post.dto.CategoryPostListResponse;
import com.example.backend.post.service.PostListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostListController extends BasePostController {

  private final PostListService postListService;

  @GetMapping("/category/list")
  public List<CategoryPostListResponse> getPostList() {
    return postListService.getPostList();
  }
}
