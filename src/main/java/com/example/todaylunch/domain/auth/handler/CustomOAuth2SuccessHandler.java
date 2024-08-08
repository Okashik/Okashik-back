package com.example.todaylunch.domain.auth.handler;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {

    @Value("${frontUri}")String frontUri;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException{
        /*
        // 세션을 생성하고, 세션 ID를 쿠키로 설정
        String sessionId = request.getSession().getId();
        ResponseCookie cookie = ResponseCookie.from("JSESSIONID", sessionId)
                .path("/")
                .sameSite("None")
                .httpOnly(true)
                .maxAge(30*60)
                .build();

        response.addHeader("Set-Cookie", cookie.toString());

        Cookie sessionCookie = new Cookie("JSESSIONID", sessionId);
        sessionCookie.setPath("/");
        sessionCookie.setHttpOnly(true);
        sessionCookie.setMaxAge(30 * 60); // 30분간 유효
        response.addCookie(sessionCookie);
         */

        // 프론트엔드 주소로 리디렉션
        log.info("Redirecting to: " + frontUri + "/oauth-callback");
        response.sendRedirect(frontUri + "/oauth-callback");
    }
}
