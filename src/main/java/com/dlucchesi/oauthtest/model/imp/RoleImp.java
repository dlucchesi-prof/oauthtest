package com.dlucchesi.oauthtest.model.imp;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity(name = "role")
@Table(name = "role")
@Data
public class RoleImp implements com.dlucchesi.oauthtest.model.Role {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;

    public RoleImp(String name){
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return null;
    }
}
