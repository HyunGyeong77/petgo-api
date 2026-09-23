package com.example.backend.post.repository;

import com.example.backend.post.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostCategoryRepository extends JpaRepository<Category, UUID> {

  long count();
}
