package com.dlucchesi.oauthtest.model;

import org.springframework.security.core.GrantedAuthority;

public interface Role extends GrantedAuthority {
    @Override
    String getAuthority();

    Long getId();

    String getName();

    void setId(Long id);

    void setName(String name);
}
