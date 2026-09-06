package com.example.backend.recommend.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "product_category")
@Getter
public class ProductCategoryRelation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private Category category;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id")
  private Product product;
}
