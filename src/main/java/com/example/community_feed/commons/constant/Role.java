package com.example.community_feed.commons.constant;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("ADMIN"), USER("USER");
    private final String name;

    Role(String name) {
        this.name = name;
    }

    public static Role of(String name) {
        for (Role role : Role.values()) {
            if (role.name.equals(name)) {
                return role;
            }
        }
        throw new IllegalArgumentException("유효하지 않은 역할");
    }
}
