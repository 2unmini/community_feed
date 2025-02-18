package com.example.community_feed.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class UserRequestDto {

    @Getter
    public static class SignUpRequestDto {

        @NotNull
        private String name;
        @Email
        private String email;
        @NotNull
        private String password;
    }

    @Getter
    public static class LoginRequestDto {

        @Email
        private String email;
        @NotNull
        private String password;
    }

}
