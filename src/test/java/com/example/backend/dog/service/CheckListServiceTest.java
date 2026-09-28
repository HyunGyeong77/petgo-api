package com.example.backend.dog.service;

import com.example.backend.dog.dto.CheckListResponse;
import com.example.backend.dog.repository.CheckListRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CheckListServiceTest {

  @Mock
  CheckListRepository checkListRepository;

  @InjectMocks
  CheckListService checkListService;

  @Test
  void 오늘의_체크리스트_조회() {
    // given
    List<CheckListResponse> checkLists = getLists();

    when(checkListRepository.findAllCheckLists()).thenReturn(checkLists);

    // when
    List<CheckListResponse> result = checkListService.getTodayCheckLists();

    // then
    assertThat(result).containsExactlyInAnyOrderElementsOf(checkLists);
  }

  List<CheckListResponse> getLists() {

    UUID id1 = UUID.fromString("00000000-0000-0000-0000-000000000001");
    UUID id2 = UUID.fromString("00000000-0000-0000-0000-000000000002");

    CheckListResponse checkList1 = new CheckListResponse(
      id1,
      "리스트1"
    );

    CheckListResponse checkList2 = new CheckListResponse(
      id2,
      "리스트2"
    );

    return List.of(checkList1, checkList2);
  }
}
