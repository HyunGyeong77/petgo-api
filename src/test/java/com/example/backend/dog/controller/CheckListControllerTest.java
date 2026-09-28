package com.example.backend.dog.controller;

import com.example.backend.dog.dto.CheckListResponse;
import com.example.backend.dog.service.CheckListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CheckListController.class)
public class CheckListControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockitoBean
  CheckListService checkListService;

  @Test
  void 오늘의_체크리스트_조회() throws Exception {
    // given
    List<CheckListResponse> lists = getLists();

    when(checkListService.getTodayCheckLists()).thenReturn(lists);

    // when & then
    mockMvc.perform(
      get("/api/dog-info/checklist/today")
    )
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.length()").value(1))
      .andExpect(jsonPath("$[0].id").value("00000000-0000-0000-0000-000000000001"))
      .andExpect(jsonPath("$[0].title").value("리스트1"));
  }

  List<CheckListResponse> getLists() {

    UUID id = UUID.fromString("00000000-0000-0000-0000-000000000001");

    CheckListResponse checkListResponse = new CheckListResponse(
      id,
      "리스트1"
    );

    return List.of(checkListResponse);
  }
}
