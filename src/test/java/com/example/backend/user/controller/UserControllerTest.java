package com.example.backend.user.controller;

import com.example.backend.user.dto.NicknameAvailabilityResponse;
import com.example.backend.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockitoBean
  UserService userService;

  @Test
  void 닉네임_중복_확인_요청에_응답한다() throws Exception {
    // given
    String nickname = "닉네임";

    NicknameAvailabilityResponse available = new NicknameAvailabilityResponse(true);

    when(userService.checkNicknameAvailability(nickname)).thenReturn(available);

    // when & then
    mockMvc.perform(
      get("/api/user/nickname-availability").param("nickname", nickname)
    )
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.available").value(true));
  }
}
