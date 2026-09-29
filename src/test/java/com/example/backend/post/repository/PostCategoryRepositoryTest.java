package com.example.backend.post.repository;

import com.example.backend.config.QuerydslConfig;
import com.example.backend.post.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(QuerydslConfig.class)
public class PostCategoryRepositoryTest {

  @Autowired
  PostCategoryRepository postCategoryRepository;

  @Test
  void 카테고리_통계를_조회한다() {
    // given
    Category category1 = new Category(
      UUID.fromString("00000000-0000-0000-0000-000000000001"),
      "테스트 제목1",
      "테스트 설명1",
      "테스트 아이콘1"
    );

    Category category2 = new Category(
      UUID.fromString("00000000-0000-0000-0000-000000000002"),
      "테스트 제목2",
      "테스트 설명2",
      "테스트 아이콘2"
    );

    postCategoryRepository.saveAll(List.of(category1, category2));

    // when
    Long result = postCategoryRepository.count();

    // then
    assertThat(result).isEqualTo(2L);
  }
}
