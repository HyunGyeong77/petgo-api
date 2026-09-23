package com.example.backend.recommend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

<<<<<<< Updated upstream:src/main/java/com/example/backend/recommend/entity/Category.java
import java.util.ArrayList;
import java.util.List;

=======
>>>>>>> Stashed changes:src/main/java/com/example/backend/recommend/entity/RecommendCategory.java
@Entity
@Table(name = "categories")
@Getter
@Setter
public class RecommendCategory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "category_id")
  private int id;

  private String type;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  private RecommendCategory parent;
}
