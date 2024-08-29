package com.beelinkers.englebee.auth.config;

import com.beelinkers.englebee.auth.domain.entity.Role;
import com.beelinkers.englebee.auth.oauth2.exception.handler.CustomAuthenticationFailureHandler;
import com.beelinkers.englebee.auth.oauth2.userinfo.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
  private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http
        .csrf(csrf -> csrf
            .ignoringRequestMatchers("/h2-console/**"))
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
            .failureHandler(customAuthenticationFailureHandler));

    // 로그아웃 설정
    http
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/main")
            .deleteCookies("JSESSIONID")
            .invalidateHttpSession(true));

    // 접근 권한 설정
    http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/", "/oauth2/**", "/login/**", "/h2-console/**", "/api/auth/**",
                "/main", "/signup", "/api/auth/signup/**")
            .permitAll()
            .requestMatchers("/chat", "/socket")
            .hasAnyRole(Role.TEACHER.name(), Role.STUDENT.name())
            .requestMatchers("/api/admin/**").hasRole(Role.ADMIN.name())
            .requestMatchers("/api/teacher/**").hasRole(Role.TEACHER.name())
            .requestMatchers("/api/student/**").hasRole(Role.STUDENT.name())
            .requestMatchers("/api/auth/signout")
            .hasAnyRole(Role.STUDENT.name(), Role.TEACHER.name())
            .anyRequest().authenticated());

    return http.build();
  }
}
