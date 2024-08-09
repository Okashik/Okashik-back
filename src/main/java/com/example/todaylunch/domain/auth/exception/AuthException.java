package com.example.todaylunch.domain.auth.exception;

import com.example.todaylunch.common.exception.BusinessException;
import lombok.Getter;

@Getter
public class AuthException extends BusinessException {
    private final AuthErrorCode authErrorCode;

    public AuthException(AuthErrorCode authErrorCode) {
        super(authErrorCode);
        this.authErrorCode = authErrorCode;
    }
}
