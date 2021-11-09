package com.dlucchesi.oauthtest.config;

import com.dlucchesi.oauthtest.model.Const;
import com.dlucchesi.oauthtest.model.Role;
import com.dlucchesi.oauthtest.model.RoleBase;
import com.dlucchesi.oauthtest.model.User;
import com.dlucchesi.oauthtest.model.imp.UserImp;
import com.dlucchesi.oauthtest.service.RoleService;
import com.dlucchesi.oauthtest.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class DataInitializr implements ApplicationListener<ContextRefreshedEvent> {

    private final UserService   userService;
    private final RoleService   roleService;
    private final PasswordEncoder   passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Optional<List<User>> usersOpt = userService.find();
        if (!usersOpt.isPresent()) {
            createUser("Admin", "admin", passwordEncoder.encode("123456"), Const.ROLE_ADMIN);
            createUser("Cliente", "cliente", passwordEncoder.encode("123456"), Const.ROLE_CLIENT);
        }
    }

    public void createUser(String name, String email, String password, String roleName) {
        Role role = roleService.getNew();
        role.setId(null);
        role.setName(roleName);
        Optional<Role> retRole = roleService.save(role);
        if (retRole.isPresent()){
            log.info("Create default role {}", retRole.get());
        } else {
            log.error("Critical error on create role!");
        }

        User user = userService.getNew();
        user.setId(null);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(Arrays.asList(role));
        Optional<User> retUser = userService.save(user);
        if (retUser.isPresent()){
            log.info("Create default user {}", retUser.get());
        } else {
            log.error("Critical error on create user!");
        }
    }
}
