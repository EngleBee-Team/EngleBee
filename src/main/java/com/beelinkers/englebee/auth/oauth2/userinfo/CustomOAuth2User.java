package com.beelinkers.englebee.auth.oauth2.userinfo;

import com.beelinkers.englebee.auth.domain.entity.Role;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;


@RequiredArgsConstructor
public class CustomOAuth2User implements OAuth2User {

  private final OAuth2Response oAuth2Response;
  private final Role role;

  @Override
  public Map<String, Object> getAttributes() {
    return Collections.emptyMap();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singletonList((GrantedAuthority) role::getKey);
  }

  @Override
  public String getName() {
    return oAuth2Response.getName();
  }
}
