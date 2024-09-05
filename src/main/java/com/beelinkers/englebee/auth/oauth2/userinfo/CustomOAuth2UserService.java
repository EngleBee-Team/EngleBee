package com.beelinkers.englebee.auth.oauth2.userinfo;

import static com.beelinkers.englebee.auth.constant.AuthConstant.SESSION_MEMBER_KEY;
import static com.beelinkers.englebee.auth.constant.AuthConstant.SIGNUP_PROGRESS_SESSION_MEMBER_KEY;

import com.beelinkers.englebee.auth.domain.entity.LoginType;
import com.beelinkers.englebee.auth.domain.entity.Member;
import com.beelinkers.englebee.auth.domain.repository.MemberRepository;
import com.beelinkers.englebee.auth.oauth2.exception.AlreadySignedUpEmailException;
import com.beelinkers.englebee.auth.oauth2.exception.DeactivatedMemberException;
import com.beelinkers.englebee.auth.oauth2.exception.SignupRequiredException;
import com.beelinkers.englebee.auth.oauth2.exception.UnsupportedSocialLoginTypeException;
import com.beelinkers.englebee.auth.oauth2.session.SessionMember;
import com.beelinkers.englebee.auth.oauth2.session.SignupProgressSessionMember;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

  private final MemberRepository memberRepository;
  private final HttpSession httpSession;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) {
    OAuth2User oAuth2User = super.loadUser(userRequest);
    log.info("oAuth2User.getAttributes() = {}", oAuth2User.getAttributes());

    String registrationId = userRequest.getClientRegistration().getRegistrationId();
    LoginType loginAttemptedType = LoginType.fromAlias(registrationId);

    OAuth2Response oAuth2Response = null;
    if (loginAttemptedType.isGoogle()) {
      oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
    } else if (loginAttemptedType.isNaver()) {
      oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
    } else {
      throw new UnsupportedSocialLoginTypeException("해당 소셜 로그인 타입은 지원하지 않습니다.");
    }

    String email = oAuth2Response.getEmail();
    Optional<Member> optionalMember = memberRepository.findByEmail(email);

    if (optionalMember.isEmpty()) {
      if (httpSession.getAttribute(SIGNUP_PROGRESS_SESSION_MEMBER_KEY) == null) {
        httpSession.setAttribute(SIGNUP_PROGRESS_SESSION_MEMBER_KEY,
            new SignupProgressSessionMember(email, loginAttemptedType));
      }
      throw new SignupRequiredException("회원가입 미완료 : 추가 회원가입 페이지로 이동");
    } else {
      Member member = optionalMember.get();

      if (!member.getLoginType().equals(loginAttemptedType)) {
        throw new AlreadySignedUpEmailException(
            "이미 다른 소셜로그인 계정으로 가입된 이메일입니다. 해당 소셜 로그인 계정으로 로그인해주세요.");
      }

      if (member.isDeactivated()) {
        throw new DeactivatedMemberException("회원 탈퇴 처리된 계정입니다.");
      }

      httpSession.invalidate();
      httpSession.setAttribute(SESSION_MEMBER_KEY,
          new SessionMember(member.getSeq(), member.getRole()));
    }
    return new CustomOAuth2User(oAuth2Response, optionalMember.get().getRole());
  }
}
