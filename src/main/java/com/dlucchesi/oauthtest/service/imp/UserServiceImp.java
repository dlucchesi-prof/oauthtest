package com.dlucchesi.oauthtest.service.imp;

import com.dlucchesi.oauthtest.model.User;
import com.dlucchesi.oauthtest.model.imp.UserImp;
import com.dlucchesi.oauthtest.repository.UserImpRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service("userService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserServiceImp implements com.dlucchesi.oauthtest.service.UserService {

    private final UserImpRepository userImpRepository;

    @Override
    public Optional<User> findByEmail(String email){
        Optional<User> ret = Optional.empty();
        UserImp tmp = userImpRepository.findByEmail(email);
        if (!isNull(tmp)){
            ret = Optional.of(tmp);
        }
        return ret;
    }

    @Override
    public Optional<List<User>> find(){
        Optional<List<User>> ret = Optional.empty();
        List<User> tmp = new ArrayList<>();
        tmp.addAll(userImpRepository.findAll());
        if (tmp.size() > 0){
            ret = Optional.of(tmp);
        }
        return ret;
    }

    @Override
    public Optional<User> save(User User){
        Optional<User> ret = Optional.empty();
        UserImp imp = validateEntity(User);
        if (!isNull(imp)){
          if (validateEntityImp(imp)){
              ret = Optional.of(userImpRepository.save(imp));
          }
        } else {
            log.error("Entity 'User' null!!!");
        }
        return ret;
    }

    @Override
    public Boolean validateEntityImp(User user){
        Boolean ret = Boolean.TRUE;
        String name = user.getName();
        if (isNull(name) || name.trim().length() <= 0){
            ret = Boolean.FALSE;
        }
        String email = user.getEmail();
        if (isNull(email) || email.trim().length() <= 0){
            ret = Boolean.FALSE;
        }
        return ret;
    }

    @Override
    public UserImp validateEntity(User entity) {
        UserImp ret = null;
        if (!isNull(entity)) {
            if (entity instanceof UserImp){
                ret = (UserImp) entity;
            }
        }
        return ret;
    }

    @Override
    public User getNew(){
        return  new UserImp();
    }
}
