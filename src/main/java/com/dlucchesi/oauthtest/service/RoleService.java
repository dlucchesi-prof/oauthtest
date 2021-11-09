package com.dlucchesi.oauthtest.service;

import com.dlucchesi.oauthtest.model.Role;
import com.dlucchesi.oauthtest.model.imp.RoleImp;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Optional<List<Role>> find();

    Optional<Role> save(Role role);

    Boolean validateEntityImp(Role role);

    RoleImp validateEntity(Role entity);

    Role getNew();
}
