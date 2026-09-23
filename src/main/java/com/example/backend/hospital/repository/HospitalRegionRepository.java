package com.example.backend.hospital.repository;

import com.example.backend.hospital.entity.HospitalRegion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalRegionRepository extends JpaRepository<HospitalRegion, String> {

  List<HospitalRegion> findByLevelIn(List<Integer> level);

  List<HospitalRegion> findByParentCodeAndLevelIn(String code, List<Integer> level);
}
