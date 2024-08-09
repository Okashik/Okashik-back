package com.example.todaylunch.domain.auth.controller;

import com.example.todaylunch.domain.auth.exception.AuthErrorCode;
import com.example.todaylunch.domain.auth.exception.AuthException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/auth/not-secured")
    public void notSecured() {
        throw new AuthException(AuthErrorCode.LOGIN_REQUIRED);
    }
}
