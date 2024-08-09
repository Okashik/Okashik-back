package com.example.todaylunch.domain.auth.handler;

import com.example.todaylunch.domain.auth.exception.AuthErrorCode;
import com.example.todaylunch.domain.auth.exception.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KakaoLogoutHandler implements LogoutHandler {

    @Value("${apiKey}")
    private String clientId;

    @Value("${frontUri}")
    private String frontUri;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 카카오 로그아웃 URL 생성
        String kakaoLogoutUrl = String.format(
                "https://kauth.kakao.com/oauth/logout?client_id=%s&logout_redirect_uri=%s",
                clientId, frontUri + "?logout=success");

        // 카카오 로그아웃 URL로 리다이렉트
        try {
            response.sendRedirect(kakaoLogoutUrl);
        } catch (Exception e) {
            log.info("Logout failed");
            throw new AuthException(AuthErrorCode.CANNOT_LOGOUT);
        }finally {
            log.info("Logout success");
        }
    }
}
