package com.example.backend.post.repository;

import com.example.backend.post.dto.CategoryPostRow;
import com.example.backend.post.dto.PostCategoryRow;

import java.util.List;
import java.util.UUID;

public interface CategoryPostRepository {

  PostCategoryRow findCategory(UUID categoryId);

  List<CategoryPostRow> findPosts(UUID categoryId);
}
