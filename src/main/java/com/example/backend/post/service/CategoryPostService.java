package com.example.backend.post.service;

import com.example.backend.post.dto.CategoryPostResponse;
import com.example.backend.post.dto.CategoryPostRow;
import com.example.backend.post.dto.PostCategoryRow;
import com.example.backend.post.repository.CategoryPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryPostService {

  private final CategoryPostRepository categoryPostRepository;

  public CategoryPostResponse getCategoryPost(UUID categoryId) {

    PostCategoryRow category = categoryPostRepository.findCategory(categoryId);

    List<CategoryPostRow> posts = categoryPostRepository.findPosts(categoryId);

    return new CategoryPostResponse(
      category.title(),
      category.description(),
      posts
    );
  }
}
