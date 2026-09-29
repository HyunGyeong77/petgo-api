package com.example.backend.post.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity(name = "PostCategory")
@Table(name = "posts_categories")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

  @Id
  @Column(name = "category_id")
  private UUID id;

  private String title;

  private String description;

  private Boolean preview;

  private String icon;

  public Category(UUID id, String title, String description, String icon) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.icon = icon;
  }
}
