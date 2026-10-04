package com.example.backend.user.service;

import com.example.backend.user.dto.NicknameAvailabilityResponse;
import com.example.backend.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  @Mock
  UserRepository userRepository;

  @InjectMocks
  UserService userService;

  @Test
  void 이미_사용_중인_닉네임이면_사용_불가를_반환한다() {
    // then
    String nickname = "닉네임";

    when(userRepository.existsByUsername(nickname)).thenReturn(true);

    // when
    NicknameAvailabilityResponse result = userService.checkNicknameAvailability(nickname);

    // then
    assertThat(result.available()).isFalse();
  }
}
