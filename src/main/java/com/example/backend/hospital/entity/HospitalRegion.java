package com.example.backend.hospital.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "regions")
@Getter
@Setter
public class HospitalRegion {

  @Id
  private String code;

  private String name;

  private int level;

  @Column(name = "parent_code")
  private String parentCode;
}
