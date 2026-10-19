package com.example.backend.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  @Column(name = "public_id", nullable = false, unique = true)
  UUID publicId;

  @Column(nullable = false, unique = true)
  String username;

  @Column(nullable = false)
  String password;

  @Column(nullable = false)
  String email;

  @Column(name = "created_at", nullable = false)
  LocalDateTime createdAt;

  public User(
    UUID publicId,
    String username,
    String password,
    String email,
    LocalDateTime createdAt
  ) {
    this.publicId = publicId;
    this.username = username;
    this.password = password;
    this.email = email;
    this.createdAt = createdAt;
  }
}
