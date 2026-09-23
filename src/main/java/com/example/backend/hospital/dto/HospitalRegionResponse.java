package com.example.backend.hospital.dto;


public record RegionResponse (
  String code,
  String name,
  int level
) {

}
