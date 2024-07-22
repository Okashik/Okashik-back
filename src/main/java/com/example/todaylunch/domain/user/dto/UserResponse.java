package com.example.todaylunch.domain.user.dto;

import com.example.todaylunch.domain.user.entity.Gender;
import com.example.todaylunch.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String nickname;
    private String email;
    private String profileImageUrl;
    private Gender gender;

    @Builder
    public UserResponse(User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.profileImageUrl = user.getProfileImageUrl();
        this.gender = user.getGender();
    }
}
