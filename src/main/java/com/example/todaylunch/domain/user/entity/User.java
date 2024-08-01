package com.example.todaylunch.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private String email;
    private String profileImageUrl;
    private String socialId;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Builder
    public User(Long id, String nickname, String email, String profileImageUrl, String socialId, Role role, Gender gender) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
        this.socialId = socialId;
        this.role = role;
        this.gender = gender;
    }

    public void updateSocialId(String socialId) {
        if(socialId!=null) {
            this.socialId = socialId;
        }
    }

    public void updateEmail(String email) {
        if(email!=null) {
            this.email = email;
        }
    }

    public void updateNickname(String nickname) {
        if(nickname!=null) {
            this.nickname = nickname;
        }
    }

    public void updateGender(Gender gender) {
        if(gender!=null){
            this.gender = gender;
        }
    }

    public void updateProfileImageUrl(String profileImageUrl) {
        if(profileImageUrl!=null) {
            this.profileImageUrl = profileImageUrl;
        }
    }
}
