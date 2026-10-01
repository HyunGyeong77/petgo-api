package com.example.backend.post.repository;

import com.example.backend.config.QuerydslConfig;
import com.example.backend.post.dto.CategoryPostRow;
import com.example.backend.post.dto.PostCategoryRow;
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

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(QuerydslConfig.class)
public class CategoryPostRepositoryTest {

  @Container
  static PostgreSQLContainer<?> postgres =
    new PostgreSQLContainer<>("postgres:16")
      .withDatabaseName("postgres")
      .withUsername("postgres")
      .withPassword("postgres");

  @DynamicPropertySource
  static void configureProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgres::getJdbcUrl);
    registry.add("spring.datasource.username", postgres::getUsername);
    registry.add("spring.datasource.password", postgres::getPassword);
  }

  @PersistenceContext
  EntityManager entityManager;

  @Autowired
  CategoryPostRepository categoryPostRepository;

  @Test
  void findCategoryRowById_존재하는_ID로_카테고리를_조회한다() {
    // given
    UUID categoryId = UUID.fromString("00000000-0000-0000-0000-0000000001");

    Category category = new Category(
      categoryId,
      "카테고리 제목1",
      "카테고리 설명1"
    );

    categoryPostRepository.save(category);

    // when
    PostCategoryRow result = categoryPostRepository.findCategoryRowById(categoryId);

    // then
    assertThat(result)
      .extracting(PostCategoryRow::title, PostCategoryRow::description)
      .containsExactly("카테고리 제목1", "카테고리 설명1");
  }

  @Test
  void findPosts_카테고리_ID와_관련있는_포스트들을_조회한다() {
    // given
    UUID categoryId = UUID.fromString("00000000-0000-0000-0000-0000000001");

    Category category = new Category(categoryId);

    categoryPostRepository.save(category);

    Post post1 = new Post(
      UUID.randomUUID(),
      "포스트 제목1",
      "1",
      3
    );

    Post post2 = new Post(
      UUID.randomUUID(),
      "포스트 제목2",
      "2",
      5
    );

    entityManager.persist(post1);
    entityManager.persist(post2);

    entityManager.persist(relation(category, post1));
    entityManager.persist(relation(category, post2));

    entityManager.flush();
    entityManager.clear();

    // when
    List<CategoryPostRow> result = categoryPostRepository.findPosts(categoryId);

    // then
    List<CategoryPostRow> expected = List.of(
      new CategoryPostRow(
        categoryId,
        post1.getId(),
        post1.getTitle(),
        post1.getLevel(),
        post1.getReadtime()
      ),
      new CategoryPostRow(
        categoryId,
        post2.getId(),
        post2.getTitle(),
        post2.getLevel(),
        post2.getReadtime()
      )
    );

    assertThat(result)
      .containsExactlyInAnyOrderElementsOf(expected);
  }

  PostCategoryRelation relation(Category category, Post post) {
    return new PostCategoryRelation(post, category);
  }
}
