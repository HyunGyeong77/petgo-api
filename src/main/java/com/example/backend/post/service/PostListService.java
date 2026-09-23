package com.example.backend.post.service;

import com.example.backend.post.dto.CategoryPostListResponse;
import com.example.backend.post.dto.CategoryPostRow;
import com.example.backend.post.repository.PostListRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostListService {

  private final PostListRepositoryCustom postRepositoryCustom;

  public List<CategoryPostListResponse> getPostList() {

    Map<UUID, List<CategoryPostRow>> postByCategory = postRepositoryCustom.findPosts().stream()
      .collect(Collectors.groupingBy(CategoryPostRow::id));

    return postByCategory.entrySet().stream()
      .map(entry -> new CategoryPostListResponse(
        entry.getKey(),
        entry.getValue()
      )).toList();
  }
}
