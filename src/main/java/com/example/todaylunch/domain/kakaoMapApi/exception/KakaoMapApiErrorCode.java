package com.example.todaylunch.domain.kakaoMapApi.exception;

import com.example.todaylunch.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum KakaoMapApiErrorCode implements ErrorCode {
    RESTAURANT_NOT_FOUND(HttpStatus.BAD_REQUEST, "식당 정보를 불러올 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
