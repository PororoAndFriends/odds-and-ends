package com.example.security_test.auth.provider;

public interface OAuth2Userinfo {

    String getProviderId();
    String getProvider();
    String getEmail();
    String getName();
}
