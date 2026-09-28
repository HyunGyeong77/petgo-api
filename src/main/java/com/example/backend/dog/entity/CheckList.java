package com.example.backend.dog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "checklists")
@Setter
@Getter
public class CheckList {

  @Id
  private UUID id;

  private String title;

  @Column(name = "created_at")
  private OffsetDateTime createdAt;
}
