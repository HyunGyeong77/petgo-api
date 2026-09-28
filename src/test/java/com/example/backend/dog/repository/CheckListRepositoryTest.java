package com.example.backend.dog.repository;

import com.example.backend.config.QuerydslConfig;
import com.example.backend.dog.dto.CheckListResponse;
import com.example.backend.dog.entity.CheckList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@DataJpaTest
@Import(QuerydslConfig.class)
public class CheckListRepositoryTest {

  @Autowired
  CheckListRepository checkListRepository;

  @Test
  void 오늘의_체크리스트_조회() {
    // given
    UUID id1 = UUID.fromString("00000000-0000-0000-0000-000000000001");
    UUID id2 = UUID.fromString("00000000-0000-0000-0000-000000000002");

    CheckList checkList1 = new CheckList();
    checkList1.setId(id1);
    checkList1.setTitle("리스트1");

    CheckList checkList2 = new CheckList();
    checkList2.setId(id2);
    checkList2.setTitle("리스트2");

    checkListRepository.saveAll(List.of(checkList1, checkList2));

    // when
    List<CheckListResponse> result = checkListRepository.findAllCheckLists();

    // then
    assertThat(result)
      .extracting(CheckListResponse::id, CheckListResponse::title)
      .containsExactlyInAnyOrder(
        tuple(id1, "리스트1"),
        tuple(id2, "리스트2")
      );
  }
}
