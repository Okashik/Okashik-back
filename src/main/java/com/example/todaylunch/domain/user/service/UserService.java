package com.example.todaylunch.domain.user.service;

import com.example.todaylunch.common.exception.BusinessException;
import com.example.todaylunch.common.exception.ErrorCode;
import com.example.todaylunch.domain.user.dto.UserRequest;
import com.example.todaylunch.domain.user.dto.UserResponse;
import com.example.todaylunch.domain.user.entity.User;
import com.example.todaylunch.domain.user.exception.UserErrorCode;
import com.example.todaylunch.domain.user.exception.UserException;
import com.example.todaylunch.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserResponse getUser(String email) {
        log.info("Call: getUser {}", email);
        return UserResponse.builder()
                .user(userRepository.findByEmail(email).orElseThrow(
                        () -> new UserException(UserErrorCode.USER_NOT_FOUND)
                ))
                .build();
    }

    @Transactional
    public UserResponse updateUser(String email, UserRequest userRequest) {
        log.info("Call: updateUser {}", email);
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UserException(UserErrorCode.USER_NOT_FOUND)
        );
        user.updateNickname(userRequest.getNickname());
        user.updateGender(userRequest.getGender());
        return UserResponse.builder()
                .user(user)
                .build();
    }
}
