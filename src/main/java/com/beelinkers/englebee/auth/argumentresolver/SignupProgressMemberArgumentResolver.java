package com.beelinkers.englebee.auth.argumentresolver;

import static com.beelinkers.englebee.auth.constant.AuthConstant.SIGNUP_PROGRESS_SESSION_MEMBER_KEY;

import com.beelinkers.englebee.auth.annotation.SignupProgressMember;
import com.beelinkers.englebee.auth.oauth2.session.SignupProgressSessionMember;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class SignupProgressMemberArgumentResolver implements HandlerMethodArgumentResolver {

  private final HttpSession httpSession;

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.hasParameterAnnotation(SignupProgressMember.class) &&
        parameter.getParameterType().equals(SignupProgressSessionMember.class);
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest,
      WebDataBinderFactory binderFactory) {
    return httpSession.getAttribute(SIGNUP_PROGRESS_SESSION_MEMBER_KEY);
  }
}
