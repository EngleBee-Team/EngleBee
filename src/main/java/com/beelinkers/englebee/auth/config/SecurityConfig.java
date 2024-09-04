package com.beelinkers.englebee.auth.config;

import com.beelinkers.englebee.auth.domain.entity.Role;
import com.beelinkers.englebee.auth.oauth2.exception.handler.CustomAuthenticationFailureHandler;
import com.beelinkers.englebee.auth.oauth2.exception.handler.CustomAuthenticationSuccessHandler;
import com.beelinkers.englebee.auth.oauth2.userinfo.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final CustomOAuth2UserService customOAuth2UserService;
  private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
  private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http
        .csrf(AbstractHttpConfigurer::disable)
        .headers(headers -> headers
            .frameOptions(FrameOptionsConfig::sameOrigin));

    http
        .formLogin(AbstractHttpConfigurer::disable)
        .httpBasic(AbstractHttpConfigurer::disable);

    // OAuth2 로그인 설정
    http
        .oauth2Login(oauth2 -> oauth2
            .loginPage("/main")
            .userInfoEndpoint(userInfoEndpointConfig ->
                userInfoEndpointConfig.userService(customOAuth2UserService))
            .successHandler(customAuthenticationSuccessHandler)
            .failureHandler(customAuthenticationFailureHandler));

    // 로그아웃 설정
    http
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/main")
            .deleteCookies("JSESSIONID")
            .invalidateHttpSession(true));

    http
        .cors(Customizer.withDefaults());

    // 접근 권한 설정
    http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/", "/oauth2/**", "/login/**", "/h2-console/**", "/api/auth/**",
                "/main", "/signup").permitAll()
            .requestMatchers("/chat/**", "/socket/**", "/ws/**", "/wss/**", "/api/chat/**")
            .permitAll()
            .requestMatchers("/api/admin/**").hasRole(Role.ADMIN.name())
            .requestMatchers("/api/teacher/**").hasRole(Role.TEACHER.name())
            .requestMatchers("/api/student/**").hasRole(Role.STUDENT.name())
            .requestMatchers("/api/auth/account/deactivate")
            .hasAnyRole(Role.STUDENT.name(), Role.TEACHER.name())
            .requestMatchers("/my/**", "/qna/**", "/exam/**")
            .hasAnyRole(Role.STUDENT.name(), Role.TEACHER.name())
            .requestMatchers("/static/**", "/assets/**", "/css/**", "/js/**", "/images/**")
            .permitAll() // 정적 자원 허용
            .anyRequest().authenticated());

    return http.build();
  }
}
