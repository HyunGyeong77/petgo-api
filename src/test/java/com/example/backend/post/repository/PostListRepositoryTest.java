package com.example.backend.post.repository;

import com.example.backend.config.QuerydslConfig;
import com.example.backend.post.dto.CategoryPostRow;
import com.example.backend.post.entity.Category;
import com.example.backend.post.entity.Post;
import com.example.backend.post.entity.PostCategoryRelation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
@ActiveProfiles("test")
@Import({
  QuerydslConfig.class,
  PostListRepositoryImpl.class
})
public class PostListRepositoryTest {

  @Container
  static PostgreSQLContainer<?> postgres =
    new PostgreSQLContainer<>("postgres:16")
      .withDatabaseName("test")
      .withUsername("test")
      .withPassword("test");

  @DynamicPropertySource
  static void postgresProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgres::getJdbcUrl);
    registry.add("spring.datasource.username", postgres::getUsername);
    registry.add("spring.datasource.password", postgres::getPassword);
  }

  @PersistenceContext
  EntityManager entityManager;

  @Autowired
  PostListRepositoryCustom postListRepository;

  @Test
  void findPosts() {
    // given
    Category category = new Category(
      UUID.fromString("00000000-0000-0000-0000-000000000001")
    );

    entityManager.persist(category);

    Post post1 = createPost(
      1,
      LocalDateTime.of(2026, 1, 3, 0, 0),
      true
    );

    Post post2 = createPost(
      2,
      LocalDateTime.of(2026, 1, 1, 0, 0),
      true
    );

    Post post3 = createPost(
      3,
      LocalDateTime.of(2026, 1, 2, 0, 0),
      false
    );

    Post post4 = createPost(
      4,
      LocalDateTime.of(2026, 1, 4, 0, 0),
      true
    );

    entityManager.persist(post1);
    entityManager.persist(post2);
    entityManager.persist(post3);
    entityManager.persist(post4);

    entityManager.persist(relation(post1, category));
    entityManager.persist(relation(post2, category));
    entityManager.persist(relation(post3, category));
    entityManager.persist(relation(post4, category));

    entityManager.flush();
    entityManager.clear();

    // when
    List<CategoryPostRow> result = postListRepository.findPosts();

    // then
    assertThat(result).hasSize(3);

    // preview = true인 Post만 조회되고,
    // createdAt 오름차순으로 정렬되어야 한다
    assertThat(result)
      .extracting(CategoryPostRow::postId)
      .containsExactly(
        post2.getId(),
        post1.getId(),
        post4.getId()
      );

    // Projection 검정
    assertThat(result.getFirst())
      .extracting(
        CategoryPostRow::categoryId,
        CategoryPostRow::postId,
        CategoryPostRow::title,
        CategoryPostRow::level,
        CategoryPostRow::readtime
      )
      .containsExactly(
        category.getId(),
        post2.getId(),
        post2.getTitle(),
        post2.getLevel(),
        post2.getReadtime()
      );
  }

  PostCategoryRelation relation (Post post, Category category) {
    return new PostCategoryRelation(post, category);
  }

  Post createPost(int index, LocalDateTime createdAt, boolean preview) {
    return new Post(
      UUID.randomUUID(),
      "테스트 제목%d".formatted(index),
      String.valueOf(index),
      createdAt,
      index,
      preview
    );
  }
}
