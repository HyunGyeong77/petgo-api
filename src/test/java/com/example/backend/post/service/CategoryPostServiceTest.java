package com.example.backend.post.service;

import com.example.backend.exception.business.BusinessException;
import com.example.backend.exception.post.PostCategoryErrorCode;
import com.example.backend.post.dto.CategoryPostResponse;
import com.example.backend.post.dto.CategoryPostRow;
import com.example.backend.post.dto.PostCategoryRow;
import com.example.backend.post.repository.CategoryPostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryPostServiceTest {

  @Mock
  CategoryPostRepository categoryPostRepository;

  @InjectMocks
  CategoryPostService categoryPostService;

  @Test
  void 카테고리의_게시글을_조회한다() {
    // given
    UUID categoryId = UUID.fromString("00000000-0000-0000-0000-000000000001");

    PostCategoryRow category = new PostCategoryRow(
      "카테고리 제목",
      "카테고리 설명"
    );

    CategoryPostRow post = new CategoryPostRow(
      categoryId,
      UUID.randomUUID(),
      "포스트 제목",
      "1",
      3
    );

    List<CategoryPostRow> posts = List.of(post);

    when(categoryPostRepository.findCategoryRowById(categoryId)).thenReturn(category);
    when(categoryPostRepository.findPosts(categoryId)).thenReturn(posts);

    // when
    CategoryPostResponse result = categoryPostService.getCategoryPost(categoryId);

    // then
    CategoryPostResponse expected = new CategoryPostResponse(
      category.title(),
      category.description(),
      posts
    );

    assertThat(result).isEqualTo(expected);

    verify(categoryPostRepository).findCategoryRowById(categoryId);
    verify(categoryPostRepository).findPosts(categoryId);
  }

  @Test
  void 존재하지_않는_카테고리를_조회하면_예외가_발생한다() {
    // given
    UUID categoryId = UUID.fromString("00000000-0000-0000-0000-000000000001");

    when(categoryPostRepository.findCategoryRowById(categoryId)).thenReturn(null);

    // when & then
    assertThatThrownBy(() ->
      categoryPostService.getCategoryPost(categoryId)
    )
      .isInstanceOfSatisfying(
        BusinessException.class,
        exception -> assertThat(exception.getErrorCode())
          .isEqualTo(PostCategoryErrorCode.CATEGORY_NOT_FOUND)
      );

    verify(categoryPostRepository).findCategoryRowById(categoryId);

    verify(categoryPostRepository, never()).findPosts(categoryId);
  }
}
