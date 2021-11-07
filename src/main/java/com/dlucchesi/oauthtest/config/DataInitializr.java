package com.dlucchesi.oauthtest.config;

import com.dlucchesi.oauthtest.model.Const;
import com.dlucchesi.oauthtest.model.Role;
import com.dlucchesi.oauthtest.model.User;
import com.dlucchesi.oauthtest.model.imp.RoleImp;
import com.dlucchesi.oauthtest.model.imp.UserImp;
import com.dlucchesi.oauthtest.repository.RoleImpRepository;
import com.dlucchesi.oauthtest.repository.UserImpRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class DataInitializr implements ApplicationListener<ContextRefreshedEvent> {

    private final UserImpRepository userImpRepository;
    private final RoleImpRepository roleImpRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        List<UserImp> users = userImpRepository.findAll();

        if (users.isEmpty()) {
            createUser("Admin", "admin", passwordEncoder.encode("123456"), Const.ROLE_ADMIN);
            createUser("Cliente", "cliente", passwordEncoder.encode("123456"), Const.ROLE_CLIENT);
        }
    }

    public void createUser(String name, String email, String password, String roleName) {

        RoleImp role = new RoleImp(roleName);

        roleImpRepository.save(role);
        UserImp user = new UserImp(name, email, password, Arrays.asList(role));
        userImpRepository.save(user);
    }
}
