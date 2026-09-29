package com.example.backend.post.repository;

import com.example.backend.config.QuerydslConfig;
import com.example.backend.post.dto.PostCategoryListResponse;
import com.example.backend.post.entity.Category;
import com.example.backend.post.entity.Post;
import com.example.backend.post.entity.PostCategoryRelation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
@ActiveProfiles("test")
@Import(QuerydslConfig.class)
public class PostCategoryListRepositoryTest {

  @Container
  static PostgreSQLContainer<?> postgres =
    new PostgreSQLContainer<>("postgres:16")
      .withDatabaseName("test")
      .withUsername("test")
      .withPassword("test");

  @DynamicPropertySource
  static void configureProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgres::getJdbcUrl);
    registry.add("spring.datasource.username", postgres::getUsername);
    registry.add("spring.datasource.password", postgres::getPassword);
  }

  @Autowired
  PostCategoryListRepository postCategoryListRepository;

  @Autowired
  PostCategoryRepository categoryRepository;

  @Autowired
  PostRepository postRepository;

  @PersistenceContext
  EntityManager entityManager;

  @Test
  void 포스트_카테고리를_조회한다() {
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

    categoryRepository.saveAll(List.of(category1, category2));

    // 포스트 8개 생성
    List<Post> posts = new ArrayList<>();

    for (int i = 0; i < 8; i++) {
      Post post = new Post(UUID.randomUUID());
      posts.add(post);
    }

    postRepository.saveAll(posts);

    // category1 -> post 5개
    for(int i = 0; i < 5; i++) {
      entityManager.persist(relation(category1, posts.get(i)));
    }

    // category2 -> post 3개
    for(int i = 5; i < 8; i++) {
      entityManager.persist(relation(category2, posts.get(i)));
    }

    entityManager.flush();
    entityManager.clear();

    // when
    List<PostCategoryListResponse> result = postCategoryListRepository.findPostCategoryList();

    // then
    assertThat(result).hasSize(2);

    PostCategoryListResponse result1 = result.stream()
      .filter(response -> response.id().equals(category1.getId()))
      .findFirst()
      .orElseThrow();

    PostCategoryListResponse result2 = result.stream()
      .filter(response -> response.id().equals(category2.getId()))
      .findFirst()
      .orElseThrow();

    assertThat(result1.postCount()).isEqualTo(5);
    assertThat(result2.postCount()).isEqualTo(3);
  }

  PostCategoryRelation relation(Category category, Post post) {
    return new PostCategoryRelation(post, category);
  }
}
