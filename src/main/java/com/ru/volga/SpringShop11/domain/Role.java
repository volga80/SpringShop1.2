package com.ru.volga.SpringShop11.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, CLIENT;

    @Override
    public String getAuthority() {
        return name();
    }
}
