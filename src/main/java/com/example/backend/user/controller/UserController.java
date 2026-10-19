package com.example.backend.user.controller;

import com.example.backend.user.dto.NicknameAvailabilityResponse;
import com.example.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/nickname-availability")
  public NicknameAvailabilityResponse checkNicknameAvailability(
    @RequestParam String nickname
  ) {
    return userService.checkNicknameAvailability(nickname);
  }
}
