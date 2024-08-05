package com.example.todaylunch.domain.user.dto;

import com.example.todaylunch.domain.user.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String nickname;
    private Gender gender;
}
