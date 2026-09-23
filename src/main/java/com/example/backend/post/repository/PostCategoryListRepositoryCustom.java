package com.example.backend.post.repository;

import com.example.backend.post.dto.PostCategoryListResponse;

import java.util.List;

public interface PostCategoryListRepositoryCustom {

  List<PostCategoryListResponse> findPostCategoryList();
}
