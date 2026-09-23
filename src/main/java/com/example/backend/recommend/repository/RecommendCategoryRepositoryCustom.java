package com.example.backend.recommend.repository;

import com.example.backend.recommend.dto.RecommendCategoryProductResponse;

import java.util.List;

public interface RecommendCategoryRepositoryCustom {

  List<RecommendCategoryProductResponse> findProductsByCategoryIds(List<Integer> categoryIds);
}
