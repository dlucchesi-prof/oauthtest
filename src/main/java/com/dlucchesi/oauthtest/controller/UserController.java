package com.dlucchesi.oauthtest.controller;

import com.dlucchesi.oauthtest.model.Const;
import com.dlucchesi.oauthtest.model.User;
import com.dlucchesi.oauthtest.model.imp.UserImp;
import com.dlucchesi.oauthtest.repository.RoleImpRepository;
import com.dlucchesi.oauthtest.repository.UserImpRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserController {

    private final UserImpRepository userImpRepository;
    private final RoleImpRepository roleImpRepository;
    private final PasswordEncoder   passwordEncoder;

    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<UserImp> save(@RequestBody UserImp user){
        UserImp ret = userImpRepository.save(user);
        return new ResponseEntity<UserImp>(ret, HttpStatus.OK);
    }

    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<UserImp> edit(@RequestBody UserImp user){
        user = userImpRepository.save(user);
        return new ResponseEntity<UserImp>(user, HttpStatus.OK);
    }

    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN})
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Page<UserImp>> list(
            @RequestParam("page") int page,
            @RequestParam("size") int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        return new ResponseEntity<Page<UserImp>>(userImpRepository.findAll(pageable), HttpStatus.OK);
    }

}
