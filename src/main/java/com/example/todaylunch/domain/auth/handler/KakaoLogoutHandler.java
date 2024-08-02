package com.example.todaylunch.domain.auth.handler;

import com.example.todaylunch.common.exception.BusinessException;
import com.example.todaylunch.common.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
public class KakaoLogoutHandler implements LogoutHandler {

    @Value("${apiKey}")
    private String clientId;

    @Value("${frontUri}")
    private String logoutRedirectUri;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 카카오 로그아웃 URL 생성
        String kakaoLogoutUrl = String.format(
                "https://kauth.kakao.com/oauth/logout?client_id=%s&logout_redirect_uri=%s",
                clientId, logoutRedirectUri);

        // 카카오 로그아웃 URL로 리다이렉트
        try {
            response.sendRedirect(kakaoLogoutUrl);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.CANNOT_LOGOUT_OAUTH_SERVICE);
        }
    }
}
