package com.example.backend.post.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Entity(name = "PostCategory")
@Table(name = "posts_categories")
@Getter
public class Category {

  @Id
  @Column(name = "category_id")
  private UUID id;

  private String title;

  private String description;

  private Boolean preview;

  private String icon;
}
