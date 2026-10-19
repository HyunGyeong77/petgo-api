package com.example.backend.user.service;

import com.example.backend.user.dto.NicknameAvailabilityResponse;
import com.example.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public NicknameAvailabilityResponse checkNicknameAvailability(String nickname) {

    boolean available = !userRepository.existsByUsername(nickname.trim());
    return new NicknameAvailabilityResponse(available);
  }
}
