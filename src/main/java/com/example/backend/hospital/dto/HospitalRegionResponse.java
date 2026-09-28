package com.example.backend.hospital.dto;

public record HospitalRegionResponse(
  String code,
  String name,
  int level
) {

}