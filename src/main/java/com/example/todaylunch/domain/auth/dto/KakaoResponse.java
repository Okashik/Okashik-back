package com.example.todaylunch.domain.auth.dto;

import java.util.Map;

public class KakaoResponse {

    private Map<String, Object> attribute;
    private Map<String, Object> kakaoAccountAttribute;
    private Map<String, Object> profileAttribute;

    public KakaoResponse(Map<String, Object> attribute) {
        this.attribute = attribute;
        this.kakaoAccountAttribute = (Map<String, Object>) attribute.get("kakao_account");
        this.profileAttribute = (Map<String, Object>) kakaoAccountAttribute.get("profile");
    }

    public String getProvider() {
        return "kakao";
    }

    public String getProviderId() {
        return attribute.get("id").toString();
    }

    public String getEmail() {
        return kakaoAccountAttribute.get("email").toString();
    }

    public String getName() {
        return profileAttribute.get("nickname").toString();
    }

    public String getProfileUrl() {
        return profileAttribute.get("profile_image_url").toString();
    }
}
