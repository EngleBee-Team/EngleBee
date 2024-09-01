package com.beelinkers.englebee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication/*(exclude = {SecurityAutoConfiguration.class,
    OAuth2ClientAutoConfiguration.class,})*/
public class EngleBeeApplication {

  public static void main(String[] args) {
    SpringApplication.run(EngleBeeApplication.class, args);
  }

}
