package com.example.backend.recommend.repository;

import com.example.backend.recommend.dto.CategoryProduct;
import com.example.backend.recommend.entity.Category;

import java.util.List;

public interface CategoryRepositoryCustom {

  List<Category> findParents();

  List<Category> findChildren(List<Integer> parentIds);

  List<CategoryProduct> findProductsByCategoryIds(List<Integer> categoryIds);
}
