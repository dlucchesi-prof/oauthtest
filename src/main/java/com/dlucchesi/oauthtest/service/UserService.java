package com.dlucchesi.oauthtest.service;

import com.dlucchesi.oauthtest.model.User;
import com.dlucchesi.oauthtest.model.imp.UserImp;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);

    Optional<List<User>> find();

    Optional<User> save(User User);

    Boolean validateEntityImp(User user);

    UserImp validateEntity(User entity);

    User getNew();
}
