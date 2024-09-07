package com.beelinkers.englebee.global;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.service.OpenAiService;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GptConfig {

  @Value("${openai.secret-key}")
  private String secretKey;

  @Bean
  public OpenAiService openAiService() {
    return new OpenAiService(secretKey, Duration.ofSeconds(60));
  }

  @Bean
  public ObjectMapper openMapper() {
    return new ObjectMapper();
  }
}