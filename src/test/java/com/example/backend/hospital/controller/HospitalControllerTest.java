package com.example.backend.hospital.controller;

import com.example.backend.hospital.dto.HospitalRegionResponse;
import com.example.backend.hospital.service.HospitalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(HospitalController.class)
public class HospitalControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockitoBean
  HospitalService hospitalService;

  @Test
  void 지역_조회() throws Exception {
    // given
    List<HospitalRegionResponse> region = getRegion();

    when(hospitalService.getRegions("테스트 코드", List.of(1))).thenReturn(region);

    // when & then
    mockMvc.perform(get("/api/hospital/regions")
        .queryParam("code", "테스트 코드")
        .queryParam("level", "1")
      )
      .andExpect(status().isOk())
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$.length()").value(1))
      .andExpect(jsonPath("$[0].code").value("테스트 코드"))
      .andExpect(jsonPath("$[0].name").value("테스트 이름"))
      .andExpect(jsonPath("$[0].level").value(1));
  }

  List<HospitalRegionResponse> getRegion() {

    HospitalRegionResponse region = new HospitalRegionResponse(
      "테스트 코드",
      "테스트 이름",
      1
    );

    return List.of(region);
  }
}
