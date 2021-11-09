package com.dlucchesi.oauthtest.model.imp;

import com.dlucchesi.oauthtest.model.AuthenticationProvider;
import com.dlucchesi.oauthtest.model.Role;
import com.dlucchesi.oauthtest.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "user")
@Table(name = "user")
@Data
public class UserImp implements com.dlucchesi.oauthtest.model.User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    @JsonIgnore
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = RoleImp.class)
    @JoinTable(name="user_role",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="role_id")
    )
    private List<Role> roles;

}
