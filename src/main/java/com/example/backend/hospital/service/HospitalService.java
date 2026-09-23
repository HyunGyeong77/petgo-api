package com.example.backend.hospital.service;

import com.example.backend.hospital.dto.HospitalRegionResponse;
import com.example.backend.hospital.entity.HospitalRegion;
import com.example.backend.hospital.repository.HospitalRegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalService {
  private final HospitalRegionRepository regionRepository;

  public List<HospitalRegionResponse> getRegions(String code, List<Integer> level) {

    List<HospitalRegion> regions;

    if (code == null)
      regions = regionRepository.findByLevelIn(level);
    else
      regions = regionRepository.findByParentCodeAndLevelIn(code, level);

    return regions.stream().map(region -> new HospitalRegionResponse(
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
