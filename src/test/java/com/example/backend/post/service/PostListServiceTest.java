package com.example.backend.post.service;

import com.example.backend.post.dto.CategoryPostListResponse;
import com.example.backend.post.dto.CategoryPostRow;
import com.example.backend.post.repository.PostListRepositoryCustom;
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
public class PostListServiceTest {

  @Mock
  PostListRepositoryCustom postListRepository;

  @InjectMocks
  PostListService postListService;

  @Test
  void 카테고리별_포스트를_조회한다() {
    // given
    UUID categoryId1 = UUID.fromString("00000000-0000-0000-0000-000000000001");
    UUID categoryId2 = UUID.fromString("00000000-0000-0000-0000-000000000002");

    CategoryPostRow post1 = new CategoryPostRow(
      categoryId1,
      UUID.fromString("00000000-0000-0000-0000-000000000001"),
      "테스트 제목1",
      "1",
      3
    );

    CategoryPostRow post2 = new CategoryPostRow(
      categoryId1,
      UUID.fromString("00000000-0000-0000-0000-000000000002"),
      "테스트 제목2",
      "2",
      5
    );

    CategoryPostRow post3 = new CategoryPostRow(
      categoryId2,
      UUID.fromString("00000000-0000-0000-0000-000000000003"),
      "테스트 제목3",
      "3",
      1
    );

    List<CategoryPostRow> posts = List.of(post1, post2, post3);

    when(postListRepository.findPosts()).thenReturn(posts);

    // when
    List<CategoryPostListResponse> result = postListService.getPostList();

    // then
    assertThat(result)
      .containsExactly(
        new CategoryPostListResponse(categoryId1, List.of(post1, post2)),
        new CategoryPostListResponse(categoryId2, List.of(post3))
      );
  }
}
