package com.example.todaylunch.domain.auth.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class CustomOAuth2FailureHandler implements AuthenticationFailureHandler {

    @Value("${frontUri}")String frontUri;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        // 로그인 실패 후 프론트엔드 주소로 리디렉션
        log.info("Redirecting to: " + frontUri + "?login=failed");
        response.sendRedirect(frontUri + "?login=failed");
    }
}