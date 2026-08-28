package com.example.backend.hospital.dto;

import lombok.Getter;

@Getter
public class RegionResponse {
  private String code;
  private String name;
  private int level;

  public RegionResponse(String code, String name, int level) {
    this.code = code;
    this.name = name;
    this.level = level;
  }
}
