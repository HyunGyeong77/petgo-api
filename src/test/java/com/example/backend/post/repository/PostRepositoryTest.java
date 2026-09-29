package com.example.backend.post.repository;

import com.example.backend.config.QuerydslConfig;
import com.example.backend.post.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(QuerydslConfig.class)
public class PostRepositoryTest {

  @Autowired
  PostRepository postRepository;

  @Test
  void 게시글_통계를_조회한다() {
    // given
    List<Post> posts = new ArrayList<>();

    for (int i = 0; i < 5; i++) {
      Post post = new Post(UUID.randomUUID());
      posts.add(post);
    }

    postRepository.saveAll(posts);

    // when
    Long result = postRepository.count();

    // then
    assertThat(result).isEqualTo(5L);
  }
}
