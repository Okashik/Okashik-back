package com.example.todaylunch.domain.user.service;

import com.example.todaylunch.common.exception.BusinessException;
import com.example.todaylunch.common.exception.ErrorCode;
import com.example.todaylunch.domain.user.dto.UserRequest;
import com.example.todaylunch.domain.user.dto.UserResponse;
import com.example.todaylunch.domain.user.entity.User;
import com.example.todaylunch.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse getUser(String email) {
        return UserResponse.builder()
                .user(userRepository.findByEmail(email).orElseThrow(
                        () -> new BusinessException(ErrorCode.INVALID_SESSION)
                ))
                .build();
    }

    @Transactional
    public UserResponse updateUser(String email, UserRequest userRequest) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new BusinessException(ErrorCode.INVALID_SESSION)
        );
        user.updateNickname(userRequest.getNickname());
        user.updateGender(userRequest.getGender());
        return UserResponse.builder()
                .user(user)
                .build();
    }
}
