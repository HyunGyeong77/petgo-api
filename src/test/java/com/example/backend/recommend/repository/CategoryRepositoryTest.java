package com.example.backend.recommend.repository;

import com.example.backend.config.QuerydslConfig;
import com.example.backend.recommend.dto.RecommendCategoryProductResponse;
import com.example.backend.recommend.entity.RecommendCategory;
import com.example.backend.recommend.entity.RecommendProduct;
import com.example.backend.recommend.entity.RecommendProductCategoryRelation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@Import(QuerydslConfig.class)
public class CategoryRepositoryTest {

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
  private TestEntityManager entityManager;

  @Autowired
  private RecommendCategoryRepository categoryRepository;

  @Test
  void 부모_카테고리를_조회한다() {
    // given
    RecommendCategory electronics = new RecommendCategory();
    electronics.setType("electronics");

    RecommendCategory clothing = new RecommendCategory();
    clothing.setType("clothing");

    RecommendCategory laptop = new RecommendCategory();
    laptop.setType("laptop");
    laptop.setParent(electronics);

    categoryRepository.save(electronics);
    categoryRepository.save(clothing);
    categoryRepository.save(laptop);

    // when
    List<RecommendCategory> result = categoryRepository.findByParentIsNull();

    // then
    assertThat(result)
      .extracting(RecommendCategory::getType)
      .containsExactlyInAnyOrder("electronics", "clothing");
  }

  @Test
  void 자식_카테고리를_조회한다() {
    // given
    RecommendCategory electronics = new RecommendCategory();
    electronics.setType("electronics");

    RecommendCategory laptop = new RecommendCategory();
    laptop.setType("laptop");
    laptop.setParent(electronics);

    RecommendCategory shirt = new RecommendCategory();
    shirt.setType("shirt");
    shirt.setParent(null);

    categoryRepository.save(electronics);
    categoryRepository.save(laptop);
    categoryRepository.save(shirt);

    List<Integer> parentIds = List.of(electronics.getId());

    // when
    List<RecommendCategory> result = categoryRepository.findByParentIdIn(parentIds);

    // then
    assertThat(result)
      .extracting(RecommendCategory::getType)
      .containsExactlyInAnyOrder("laptop");
  }

  @Test
  void 카테고리_ID로_상품을_조회한다() {
    // given
    RecommendCategory category = new RecommendCategory();
    category.setType("테스트 카테고리");

    RecommendProduct product = new RecommendProduct();
    product.setName("테스트");
    product.setDescription("테스트 상품 설명");
    product.setImage("https://test-url/image.jpg");
    product.setPrice(32000);

    RecommendProductCategoryRelation relation = new RecommendProductCategoryRelation();
    relation.setCategory(category);
    relation.setProduct(product);

    entityManager.persist(category);
    entityManager.persist(product);
    entityManager.persist(relation);

    entityManager.flush();

    List<Integer> categoryIds = List.of(category.getId());

    List<RecommendCategoryProductResponse> expected = List.of(getCategoryProduct(category.getId(), product));

    entityManager.clear();

    // when
    List<RecommendCategoryProductResponse> result = categoryRepository.findProductsByCategoryIds(categoryIds);

    // then
    assertThat(result).usingRecursiveComparison().isEqualTo(expected);
  }

  RecommendCategoryProductResponse getCategoryProduct(Integer categoryId, RecommendProduct product) {
    return new RecommendCategoryProductResponse(
      categoryId,
      product.getId(),
      product.getName(),
      product.getDescription(),
      product.getImage(),
      product.getPrice()
    );
  }
}
