package com.example.backend.recommend.repository;

import com.example.backend.recommend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository
  extends JpaRepository<Category, Integer>, CategoryRepositoryCustom {
}
