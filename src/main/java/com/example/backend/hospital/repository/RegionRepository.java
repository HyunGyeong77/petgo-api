package com.example.backend.hospital.repository;

import com.example.backend.hospital.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, String> {
  List<Region> findByParentCodeAndLevelIn(String code, List<Integer> level);
}
