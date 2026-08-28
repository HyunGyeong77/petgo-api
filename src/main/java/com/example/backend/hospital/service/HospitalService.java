package com.example.backend.hospital.service;

import com.example.backend.hospital.dto.RegionResponse;
import com.example.backend.hospital.entity.Region;
import com.example.backend.hospital.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalService {
  private final RegionRepository regionRepository;

  public List<RegionResponse> getRegions(String code, List<Integer> level) {
    List<Region> regions = regionRepository.findByParentCodeAndLevelIn(code, level);

    return regions.stream().map(region -> new RegionResponse(
      region.getCode(),
      region.getName(),
      region.getLevel()
    )).toList();
  }
}
