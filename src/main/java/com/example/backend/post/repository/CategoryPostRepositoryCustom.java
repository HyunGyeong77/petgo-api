package com.example.backend.post.repository;

import com.example.backend.post.dto.CategoryPostRow;

import java.util.List;
import java.util.UUID;

public interface CategoryPostRepositoryCustom {

  List<CategoryPostRow> findPosts(UUID categoryId);
}
