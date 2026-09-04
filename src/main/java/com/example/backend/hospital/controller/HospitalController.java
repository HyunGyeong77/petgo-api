package com.example.backend.hospital.controller;

import com.example.backend.hospital.dto.RegionResponse;
import com.example.backend.hospital.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hospital")
public class HospitalController {
  private final HospitalService hospitalService;

  @GetMapping("/regions")
  public List<RegionResponse> getRegions(
    @RequestParam(required = false) String code,
    @RequestParam List<Integer> level
  ) {
    return hospitalService.getRegions(code, level);
  }
}
