package com.beelinkers.englebee.auth.oauth2.userinfo;

import java.io.Serializable;
import java.util.Map;

public class GoogleResponse implements OAuth2Response, Serializable {

  private final Map<String, Object> attributes;

  public GoogleResponse(Map<String, Object> attributes) {
    this.attributes = attributes;
  }

  @Override
  public String getProvider() {
    return "google";
  }

  @Override
  public String getEmail() {
    return attributes.get("email").toString();
  }

  @Override
  public String getName() {
    return "google-" + attributes.get("email").toString();
  }
}
