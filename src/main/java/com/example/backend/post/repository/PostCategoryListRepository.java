package com.example.backend.post.repository;

import com.example.backend.post.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostCategoryListRepository extends
  JpaRepository<Category, UUID>, PostCategoryListRepositoryCustom {
}
