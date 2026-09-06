package com.example.backend.recommend.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
public class Product {

  @Id
  @Column(name = "product_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int productId;

  private String name;

  private String description;

  private String image;

  private int price;
}
