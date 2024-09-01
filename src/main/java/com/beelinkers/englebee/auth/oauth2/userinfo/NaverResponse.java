package com.beelinkers.englebee.auth.oauth2.userinfo;

import java.io.Serializable;
import java.util.Map;

public class NaverResponse implements OAuth2Response, Serializable {

  private final Map<String, Object> attributes;

  public NaverResponse(Map<String, Object> attributes) {
    this.attributes = (Map<String, Object>) attributes.get("response");
  }

  @Override
  public String getProvider() {
    return "naver";
  }

  @Override
  public String getEmail() {
    return attributes.get("email").toString();
  }

  @Override
  public String getName() {
    return "naver-" + attributes.get("email").toString();
  }
}