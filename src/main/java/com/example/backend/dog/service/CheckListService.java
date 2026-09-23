package com.example.backend.dog.service;

import com.example.backend.dog.dto.CheckListResponse;
import com.example.backend.dog.repository.CheckListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CheckListService {

  private final CheckListRepository checkListRepository;

  public List<CheckListResponse> getTodayCheckLists() {

    // 오늘 날짜를 기준으로 seed 생성
    long seed = LocalDate.now().toEpochDay();

    // 전체 체크리스트 조회
    List<CheckListResponse> checkLists = checkListRepository.findAllCheckLists();

    // 오늘 날짜에 따라 결정적인 랜덤 순서 생성
    Collections.shuffle(checkLists, new Random(seed));

    // 5개 반환
    return checkLists.stream().limit(5).toList();
  }
}
