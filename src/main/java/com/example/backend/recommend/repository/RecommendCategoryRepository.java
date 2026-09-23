package com.example.backend.recommend.repository;

import com.example.backend.recommend.entity.RecommendCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendCategoryRepository
  extends JpaRepository<RecommendCategory, Integer>, RecommendCategoryRepositoryCustom {

  List<RecommendCategory> findByParentIsNull();

  List<RecommendCategory> findByParentIdIn(List<Integer> categoryIds);
}
