package com.example.backend.post.repository;

import com.example.backend.post.dto.CategoryPostRow;

import java.util.List;

public interface PostListRepositoryCustom {

  List<CategoryPostRow> findPosts();
}
