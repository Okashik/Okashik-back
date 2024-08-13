package com.example.todaylunch.common.config;

import com.example.todaylunch.domain.auth.handler.CustomAuthenticationEntryPoint;
import com.example.todaylunch.domain.auth.handler.CustomOAuth2SuccessHandler;
import com.example.todaylunch.domain.auth.handler.KakaoLogoutHandler;
import com.example.todaylunch.domain.auth.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService oAuth2UserService;
    private final KakaoLogoutHandler kakaoLogoutHandler;
    private final CustomOAuth2SuccessHandler customOAuth2SuccessHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    @Value("${frontUri}") String frontUri;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() { // security를 적용하지 않을 리소스
        return web -> web.ignoring()
                // error endpoint를 열어줘야 함, favicon.ico 추가!
                .requestMatchers("/error", "/favicon.ico", "/auth/not-secured");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // CORS 설정 추가
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));

        // CSRF 보호 비활성화
        http.csrf(csrf -> csrf.disable());

        // 폼 로그인 비활성화
        http.formLogin(login -> login.disable());

        // HTTP Basic 인증 비활성화
        http.httpBasic(basic -> basic.disable());

        // OAuth2 로그인 설정
        http.oauth2Login(oauth2 -> oauth2
                // 커스텀한 서비스 클래스를 설정
                .successHandler(customOAuth2SuccessHandler)
                .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
                        .userService(oAuth2UserService)));

        // 세션 관리 설정 추가
        http.sessionManagement(sessionManagement -> sessionManagement
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .sessionFixation().none());

        // 쿠키 도메인 설정
        http.headers(headers -> headers
                .addHeaderWriter((request, response) -> {
                    String domain = "okasik.store";
                    if (request.getServletPath().startsWith("/v1")) {
                        response.setHeader("Set-Cookie", "JSESSIONID=" + request.getSession().getId() + "; Domain=" + domain + "; Path=/; Secure; HttpOnly; SameSite=None");
                    }
                }));

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll());

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/oauth2/**", "/login", "/v1/logout/kakao", "/v1/login/kakao", "/v1/category", "/v1/category/list/**").permitAll()
                .anyRequest().authenticated()
        );

        http.logout(logout -> logout.logoutUrl("/v1/logout/kakao")
                .addLogoutHandler(kakaoLogoutHandler)
                .logoutSuccessUrl("/")
                .permitAll());

        http.exceptionHandling(exception -> exception.authenticationEntryPoint(customAuthenticationEntryPoint));

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOriginPattern(frontUri); // 프론트 서버 주소
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        config.addExposedHeader("x-auth-token");
        config.setAllowCredentials(true);
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
