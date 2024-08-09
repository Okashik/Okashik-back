package com.example.todaylunch.domain.user.controller;

import com.example.todaylunch.domain.auth.dto.CustomOAuth2User;
import com.example.todaylunch.domain.user.dto.UserRequest;
import com.example.todaylunch.domain.user.dto.UserResponse;
import com.example.todaylunch.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/v1/user")
    @ResponseBody
    public ResponseEntity<UserResponse> getUser(
            @AuthenticationPrincipal CustomOAuth2User customOAuth2User) {
        return ResponseEntity.ok()
                .body(userService.getUser(customOAuth2User.getEmail()));
    }

    @PostMapping("/v1/user")
    @ResponseBody
    public ResponseEntity<UserResponse> updateUser(
            @AuthenticationPrincipal CustomOAuth2User customOAuth2User,
            @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok()
                .body(userService.updateUser(customOAuth2User.getEmail(), userRequest));
    }

    /*
    @GetMapping("/v1/login/kakao")
    public String login() {
        return "redirect:/oauth2/authorization/kakao";
    }
     */
}
