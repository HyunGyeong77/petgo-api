package com.example.backend.post.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "posts_category")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostCategoryRelation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "posts_id")
  private Post post;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private Category category;

  public PostCategoryRelation(Post post, Category category) {
    this.post = post;
    this.category = category;
  }
}
