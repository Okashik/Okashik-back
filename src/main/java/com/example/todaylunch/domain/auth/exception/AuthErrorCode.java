package com.example.todaylunch.domain.auth.exception;

import com.example.todaylunch.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthErrorCode implements ErrorCode {
    CANNOT_LOGOUT(HttpStatus.BAD_REQUEST, "로그아웃할 수 없습니다."),
    LOGIN_REQUIRED(HttpStatus.UNAUTHORIZED, "로그인이 필요한 서비스입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
