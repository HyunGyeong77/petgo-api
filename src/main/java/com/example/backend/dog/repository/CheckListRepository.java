package com.example.backend.dog.repository;

import com.example.backend.dog.dto.CheckListResponse;
import com.example.backend.dog.entity.CheckList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CheckListRepository extends JpaRepository<CheckList, UUID> {

  @Query("""
    SELECT new com.example.backend.dog.dto.CheckListResponse(
        c.id,
        c.title
    )
    FROM CheckList c
    ORDER BY c.id
""")
  List<CheckListResponse> findAllCheckLists();
}
