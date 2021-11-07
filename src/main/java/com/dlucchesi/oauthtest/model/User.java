package com.dlucchesi.oauthtest.model;

public interface User {
    Long getId();

    String getName();

    String getEmail();

    String getPassword();

    java.util.List<Role> getRoles();

    void setId(Long id);

    void setName(String name);

    void setEmail(String email);

    void setPassword(String password);

    void setRoles(java.util.List<Role> roles);
}
