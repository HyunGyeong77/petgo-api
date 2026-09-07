package com.example.backend.post.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "posts")
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

  @OneToMany(mappedBy = "post")
  private List<PostCategoryRelation> categories;
}
