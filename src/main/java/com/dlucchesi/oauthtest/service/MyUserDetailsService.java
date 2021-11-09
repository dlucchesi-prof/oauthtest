package com.dlucchesi.oauthtest.service;

import com.dlucchesi.oauthtest.model.User;
import com.dlucchesi.oauthtest.model.imp.UserImp;
import com.dlucchesi.oauthtest.repository.UserImpRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    private final UserService   userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = userService.findByEmail(username);
        if (!userOpt.isPresent()) {
            throw new UsernameNotFoundException(String.format("Usuário não existe!", username));
        } else {
            return new UserRepositoryUserDetails(userOpt.get());
        }
    }

    private final static class UserRepositoryUserDetails extends UserImp implements UserDetails {

        private UserRepositoryUserDetails(User user) {
            super();
            this.setId(user.getId());
            this.setName(user.getName());
            this.setEmail(user.getEmail());
            this.setPassword(user.getPassword());
            this.setRoles(user.getRoles());
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return getRoles();
        }

        @Override
        public String getUsername() {
            return this.getEmail();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

        @Override
        public String getPassword() {
            return  super.getPassword();
        }

    }
}
