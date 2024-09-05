package com.beelinkers.englebee.auth.oauth2.exception.handler;

import com.beelinkers.englebee.auth.oauth2.exception.AlreadySignedUpEmailException;
import com.beelinkers.englebee.auth.oauth2.exception.DeactivatedMemberException;
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

    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");

    String errorMessage;
    String redirectUrl = "/main"; // 기본 리다이렉트 URL

    if (exception instanceof SignupRequiredException) {
      // 추가회원가입 필요시 이동 처리
      errorMessage = "추가회원가입 페이지로 이동합니다.";
      redirectUrl = "/signup"; // 회원가입 페이지로 리다이렉트
    } else if (exception instanceof UnsupportedSocialLoginTypeException) {
      // 구글, 네이버 이외의 소셜 로그인 타입
      errorMessage = "해당 소셜 로그인 타입은 지원하지 않습니다.";
    } else if (exception instanceof AlreadySignedUpEmailException) {
      // 이미 다른 소셜 로그인 타입으로 회원가입 완료된 경우
      errorMessage = "이미 다른 소셜로그인 계정으로 가입된 이메일입니다. 해당 소셜 로그인 계정으로 로그인해주세요.";
    } else if (exception instanceof DeactivatedMemberException) {
      // 회원탈퇴 처리된 계정인 경우
      errorMessage = "회원 탈퇴 처리된 계정입니다.";
    } else {
      // 그 밖의 예외 처리
      errorMessage = "인증에 실패했습니다.";
    }
    
    String htmlResponse = "<html>" +
        "<head><meta charset='UTF-8'></head>" +
        "<body>" +
        "<script>" +
        "alert('" + errorMessage + "');" +
        "window.location.href = '" + redirectUrl + "';" +
        "</script>" +
        "</body>" +
        "</html>";

    // HTML 응답 작성
    response.getWriter().write(htmlResponse);
  }


}
