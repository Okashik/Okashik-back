package com.example.todaylunch.domain.kakaoMapApi.exception;

import com.example.todaylunch.common.exception.BusinessException;
import lombok.Getter;

@Getter
public class KakaoMapApiException extends BusinessException {

    private final KakaoMapApiErrorCode kakaoMapApiErrorCode;

    public KakaoMapApiException(KakaoMapApiErrorCode kakaoMapApiErrorCode) {
        super(kakaoMapApiErrorCode);
        this.kakaoMapApiErrorCode = kakaoMapApiErrorCode;
    }
}
