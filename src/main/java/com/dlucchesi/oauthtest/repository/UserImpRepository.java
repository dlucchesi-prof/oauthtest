package com.dlucchesi.oauthtest.repository;

import com.dlucchesi.oauthtest.model.imp.UserImp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserImpRepository extends JpaRepository<UserImp, Long> {
        UserImp findByEmail(String email);
}