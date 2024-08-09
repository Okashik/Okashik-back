package com.example.todaylunch.domain.user.exception;

import com.example.todaylunch.common.exception.BusinessException;
import lombok.Getter;

@Getter
public class UserException extends BusinessException {

    private final UserErrorCode userErrorCode;

    public UserException(UserErrorCode userErrorcode) {
        super(userErrorcode);
        this.userErrorCode = userErrorcode;
    }
}
