package com.example.backend.hospital.repository;

import com.example.backend.config.QuerydslConfig;
import com.example.backend.hospital.entity.HospitalRegion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@DataJpaTest
@Import(QuerydslConfig.class)
public class HospitalRepositoryTest {

  @Autowired
  HospitalRegionRepository regionRepository;

  @Test
  void 지역을_조회한다() {
    // given
    HospitalRegion level1 = new HospitalRegion();
    level1.setCode("110001");
    level1.setLevel(1);

    HospitalRegion level2 = new HospitalRegion();
    level2.setCode("110002");
    level2.setLevel(2);

    regionRepository.saveAll(List.of(level1, level2));

    List<Integer> levels = List.of(1);

    // when
    List<HospitalRegion> result = regionRepository.findByLevelIn(levels);

    // then
    assertThat(result)
      .extracting(HospitalRegion::getLevel)
      .containsExactly(1);
  }

  @Test
  void 부모_지역으로_지역을_조회한다() {
    // given
    HospitalRegion regionCodeLevel1 = new HospitalRegion();
    regionCodeLevel1.setCode("110001");
    regionCodeLevel1.setLevel(1);
    regionCodeLevel1.setParentCode("110000");

    HospitalRegion regionCodeLevel2 = new HospitalRegion();
    regionCodeLevel2.setCode("210001");
    regionCodeLevel2.setLevel(2);
    regionCodeLevel2.setParentCode("200000");

    regionRepository.saveAll(List.of(regionCodeLevel1, regionCodeLevel2));

    String parentCode = "110000";
    List<Integer> levels = List.of(1);

    // when
    List<HospitalRegion> result = regionRepository.findByParentCodeAndLevelIn(parentCode, levels);

    // then
    assertThat(result)
      .extracting(HospitalRegion::getCode, HospitalRegion::getLevel)
      .containsExactly(tuple("110001", 1));
  }
}
