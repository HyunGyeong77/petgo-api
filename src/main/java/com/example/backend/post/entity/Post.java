package com.example.backend.post.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "posts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post {

  @Id
  private UUID id;

  private String title;

  private String content;

  private String level;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  private Integer readtime;

  private Boolean preview;

  public Post(UUID id) {
    this.id = id;
  }

  public Post(
    UUID id,
    String title,
    String level,
    LocalDateTime createdAt,
    Integer readtime,
    Boolean preview
  ) {
    this.id = id;
    this.title = title;
    this.level = level;
    this.createdAt = createdAt;
    this.readtime = readtime;
    this.preview = preview;
  }
}
