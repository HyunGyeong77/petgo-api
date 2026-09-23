package com.example.backend.dog.controller;

import com.example.backend.dog.dto.CheckListResponse;
import com.example.backend.dog.service.CheckListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dog-info")
public class CheckListController {

  private final CheckListService checkListService;

  @GetMapping("/checklist/today")
  public List<CheckListResponse> getTodayCheckLists() {
    return checkListService.getTodayCheckLists();
  }
}
