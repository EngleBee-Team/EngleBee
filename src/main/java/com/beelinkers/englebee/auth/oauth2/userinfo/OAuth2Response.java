package com.beelinkers.englebee.auth.oauth2.userinfo;

public interface OAuth2Response {

  String getProvider();

  String getEmail();

  String getName();

}
