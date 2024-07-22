package com.example.todaylunch.domain.kakaoMapApi.controller;

import com.example.todaylunch.domain.kakaoMapApi.entity.Category;
import com.example.todaylunch.domain.kakaoMapApi.dto.KakaoMapApiRequest;
import com.example.todaylunch.domain.kakaoMapApi.dto.KakaoMapApiResponse;
import com.example.todaylunch.domain.kakaoMapApi.service.ApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/kakao/v1/category")
public class ApiController {

    private final ApiService apiService;

    @PostMapping("/")
    public ResponseEntity<KakaoMapApiResponse> getRandomFromApi(
            @RequestBody KakaoMapApiRequest kakaoMapApiRequest
    ) throws Exception {
        return ResponseEntity.ok()
                .body(apiService.getRandomFromApi(kakaoMapApiRequest));
    }

    @GetMapping("/list/{category}")
    public ResponseEntity<List<KakaoMapApiResponse>> getListFromApi(
            @PathVariable("category")Category category
    ) throws Exception {
        return ResponseEntity.ok()
                .body(apiService.getListFromApi(category));
    }
}
