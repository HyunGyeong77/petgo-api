package com.example.backend.post.service;

import com.example.backend.post.dto.PostCategoryListResponse;
import com.example.backend.post.repository.PostCategoryListRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostCategoryListServiceTest {

  @Mock
  PostCategoryListRepository categoryRepository;

  @InjectMocks
  PostCategoryListService categoryService;

  @Test
  void 포스트_카테고리를_조회한다() {
    // given
    List<PostCategoryListResponse> categories = getCategories();

    when(categoryRepository.findPostCategoryList()).thenReturn(categories);

    // when
    List<PostCategoryListResponse> result = categoryService.getCategories();

    // then
    assertThat(result)
      .usingRecursiveComparison()
      .isEqualTo(categories);
  }

  List<PostCategoryListResponse> getCategories() {

    UUID id1 = UUID.fromString("00000000-0000-0000-0000-000000000001");
    UUID id2 = UUID.fromString("00000000-0000-0000-0000-000000000002");

    PostCategoryListResponse category1 = new PostCategoryListResponse(
      id1,
      "테스트 제목1",
      "테스트 설명1",
      "테스트 아이콘1",
      5L
    );

    PostCategoryListResponse category2 = new PostCategoryListResponse(
      id2,
      "테스트 제목2",
      "테스트 설명2",
      "테스트 아이콘2",
      3L
    );

    return List.of(category1, category2);
  }
}
