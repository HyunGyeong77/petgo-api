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
    List<Region> regions;

    if (code == null)
      regions = regionRepository.findByLevelIn(level);
    else
      regions = regionRepository.findByParentCodeAndLevelIn(code, level);

    return regions.stream().map(region -> new RegionResponse(
      region.getCode(),
      getShortName(region.getName()),
      region.getLevel()
    )).toList();
  }

  private String getShortName(String name) {
    String[] names = name.split(" ");
    return names[names.length - 1];
  }
}
