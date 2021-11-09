package com.dlucchesi.oauthtest.service.imp;

import com.dlucchesi.oauthtest.model.Role;
import com.dlucchesi.oauthtest.model.imp.RoleImp;
import com.dlucchesi.oauthtest.repository.RoleImpRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service("roleService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class RoleServiceImp implements com.dlucchesi.oauthtest.service.RoleService {

    private final RoleImpRepository  roleImpRepository;

    @Override
    public Optional<List<Role>> find(){
        Optional<List<Role>> ret = Optional.empty();
        List<Role> tmp = new ArrayList<>();
        tmp.addAll(roleImpRepository.findAll());
        if (tmp.size() > 0){
            ret = Optional.of(tmp);
        }
        return ret;
    }

    @Override
    public Optional<Role> save(Role role){
        Optional<Role> ret = Optional.empty();
        RoleImp imp = validateEntity(role);
        if (!isNull(imp)){
          if (validateEntityImp(imp)){
              ret = Optional.of(roleImpRepository.save(imp));
          }
        } else {
            log.error("Entity 'Role' null!!!");
        }
        return ret;
    }

    @Override
    public Boolean validateEntityImp(Role role){
        Boolean ret = Boolean.TRUE;
        String name = role.getName();
        if (isNull(name) || name.trim().length() <= 0){
            ret = Boolean.FALSE;
        }
        return ret;
    }

    @Override
    public RoleImp validateEntity(Role entity) {
        RoleImp ret = null;
        if (!isNull(entity)) {
            if (entity instanceof RoleImp){
                ret = (RoleImp) entity;
            }
        }
        return ret;
    }

    @Override
    public Role getNew(){
        return new RoleImp();
    }

}
