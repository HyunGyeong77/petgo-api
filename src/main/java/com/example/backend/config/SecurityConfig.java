package com.example.backend.config;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

  private final CorsProperties corsProperties;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http
      .cors(cors -> {})
      .csrf(AbstractHttpConfigurer::disable)
      .authorizeHttpRequests(auth ->
        auth.anyRequest().permitAll()
      );

    return http.build();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {

    // CORS 설정을 담을 객체 생성
    CorsConfiguration configuration = new CorsConfiguration();

    // 어떤 출처에서 오는 요청을 허용할지 설정
    configuration.setAllowedOrigins(corsProperties.getAllowedOrigins());

    // 메서드 허용
    configuration.setAllowedMethods(List.of(
      "GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"
    ));

    // 요청에 포함되는 HTTP Header를 전부 허용
    configuration.setAllowedHeaders(List.of("*"));

    // 쿠키나 인증 정보 같은 credentials를 포함한 요청을 허용
    configuration.setAllowCredentials(true);

    // 방금 만든 CORS 설정을 어떤 URL에 적용할지 관리하는 객체 생성
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

    // 모든 URL에 이 CORS 설정을 적용
    source.registerCorsConfiguration("/**", configuration);

    return source;
  }
}
