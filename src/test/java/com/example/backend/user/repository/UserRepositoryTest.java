package com.example.backend.user.repository;

import com.example.backend.config.QuerydslConfig;
import com.example.backend.user.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(QuerydslConfig.class)
public class UserRepositoryTest {

  @Autowired
  UserRepository userRepository;

  @Test
  void username이_존재하면_true를_반환한다() {
    // given
    String username = "닉네임";

    userRepository.saveAndFlush(new User(
      UUID.randomUUID(),
      username,
      "test-password-hash",
      "test@example.com",
      LocalDateTime.of(2026, 1, 1, 0, 0)
    ));

    // when
    boolean result = userRepository.existsByUsername(username);

    // then
    assertThat(result).isTrue();
  }
}
