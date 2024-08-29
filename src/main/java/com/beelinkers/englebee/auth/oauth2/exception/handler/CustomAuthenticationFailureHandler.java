package com.beelinkers.englebee.auth.oauth2.exception.handler;

import com.beelinkers.englebee.auth.oauth2.exception.AlreadySignedUpEmailException;
import com.beelinkers.englebee.auth.oauth2.exception.SignupRequiredException;
import com.beelinkers.englebee.auth.oauth2.exception.UnsupportedSocialLoginTypeException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException exception) throws IOException, ServletException {

    if (exception instanceof SignupRequiredException) {
      // 추가회원가입 필요시 이동 처리
      response.sendRedirect("/signup");
    } else if (exception instanceof UnsupportedSocialLoginTypeException) {
      // 구글, 네이버 이외의 소셜 로그인 타입
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().write(exception.getMessage());
    } else if (exception instanceof AlreadySignedUpEmailException) {
      // 이미 다른 소셜 로그인 타입으로 회원가입 완료된 경우
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().write(exception.getMessage());
    } else {
      // 그 밖 예외 처리
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "인증에 실패했습니다.");
    }
  }
}
