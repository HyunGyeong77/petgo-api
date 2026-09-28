package com.example.backend.hospital.service;

import com.example.backend.hospital.dto.HospitalRegionResponse;
import com.example.backend.hospital.entity.HospitalRegion;
import com.example.backend.hospital.repository.HospitalRegionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HospitalServiceTest {

  @Mock
  HospitalRegionRepository hospitalRegionRepository;

  @InjectMocks
  HospitalService hospitalService;

  @Test
  void 지역을_조회한다() {
    // given
    HospitalRegion region = new HospitalRegion();
    region.setCode("110001");
    region.setName("서울특별시 강남구");
    region.setLevel(1);

    List<Integer> levels = List.of(1);

    when(hospitalRegionRepository.findByLevelIn(levels)).thenReturn(List.of(region));

    // when
    List<HospitalRegionResponse> result = hospitalService.getRegions(null, levels);

    // then
    List<HospitalRegionResponse> expected = List.of(new HospitalRegionResponse(
      "110001",
      "강남구",
      1
    ));

    assertThat(result).containsExactlyElementsOf(expected);

    verify(hospitalRegionRepository).findByLevelIn(levels);
  }

  @Test
  void 부모_지역으로_지역을_조회한다() {
    // given
    HospitalRegion region = new HospitalRegion();
    region.setCode("110001");
    region.setName("서울특별시 강남구");
    region.setLevel(1);

    String parentCode = "110000";
    List<Integer> levels = List.of(1);

    when(hospitalRegionRepository.findByParentCodeAndLevelIn(parentCode, levels))
      .thenReturn(List.of(region));

    // when
    List<HospitalRegionResponse> result = hospitalService.getRegions(parentCode, levels);

    // then
    List<HospitalRegionResponse> expected = List.of(new HospitalRegionResponse(
      "110001",
      "강남구",
      1
    ));

    assertThat(result).containsExactlyElementsOf(expected);

    verify(hospitalRegionRepository).findByParentCodeAndLevelIn(parentCode, levels);
  }
}
