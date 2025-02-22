package com.beelinkers.englebee.auth.config;

import com.beelinkers.englebee.auth.argumentresolver.LoginMemberArgumentResolver;
import com.beelinkers.englebee.auth.argumentresolver.SignupProgressMemberArgumentResolver;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

  private final LoginMemberArgumentResolver loginMemberArgumentResolver;
  private final SignupProgressMemberArgumentResolver signupProgressMemberArgumentResolver;

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(loginMemberArgumentResolver);
    resolvers.add(signupProgressMemberArgumentResolver);
  }
}
