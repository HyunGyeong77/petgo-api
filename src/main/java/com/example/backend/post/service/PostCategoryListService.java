package com.example.backend.post.service;

import com.example.backend.post.dto.PostCategoryListResponse;
import com.example.backend.post.repository.PostCategoryListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostCategoryListService {

  private final PostCategoryListRepository postCategoryListRepository;

  public List<PostCategoryListResponse> getCategories() {
    return postCategoryListRepository.findPostCategoryList();
  }
}
