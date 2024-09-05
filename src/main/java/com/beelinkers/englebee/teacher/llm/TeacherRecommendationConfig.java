package com.beelinkers.englebee.teacher.llm;

import java.util.Random;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TeacherRecommendationConfig {
  
  @Bean
  public Random random() {
    return new Random();
  }
}
