package com.example.backend.post.repository;

import com.example.backend.post.dto.PostCategoryRow;
import com.example.backend.post.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface CategoryPostRepository
  extends JpaRepository<Category, UUID>, CategoryPostRepositoryCustom {

  @Query("""
    SELECT new com.example.backend.post.dto.PostCategoryRow(
        c.title,
        c.description
    )
    FROM PostCategory c
    WHERE c.id = :categoryId
  """)
  PostCategoryRow findCategoryRowById(UUID categoryId);
}
