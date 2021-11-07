package com.dlucchesi.oauthtest.repository;

import com.dlucchesi.oauthtest.model.imp.RoleImp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleImpRepository extends JpaRepository<RoleImp, Long> {

    RoleImp findByName(String name);
}
