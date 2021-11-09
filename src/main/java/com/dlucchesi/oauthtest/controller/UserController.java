package com.dlucchesi.oauthtest.controller;

import com.dlucchesi.oauthtest.model.Const;
import com.dlucchesi.oauthtest.model.User;
import com.dlucchesi.oauthtest.service.RoleService;
import com.dlucchesi.oauthtest.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserController {

    private final UserService   userService;
    private final RoleService   roleService;

    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<User> save(@RequestBody User user){
        Optional<User> userOpt = userService.save(user);
        if (userOpt.isPresent()){
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<User> edit(@RequestBody User user){
        Optional<User> userOpt = userService.save(user);
        if (userOpt.isPresent()){
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN})
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Page<User>> list(
            @RequestParam("page") int page,
            @RequestParam("size") int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        Optional<List<User>> userListOpt = userService.find();
        Page<User> tmpPage = null;
        if (userListOpt.isPresent()){
            List<User> userList = userListOpt.get();
            int start = (userList.size() / size) * page;
            int end = start + (size - 1);
            tmpPage = new PageImpl<User>(userList.subList(start, end), pageable, userList.size());
        }
        return new ResponseEntity<Page<User>>(tmpPage, HttpStatus.OK);
    }


    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN})
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<User>> list(){
        Optional<List<User>> userListOpt = userService.find();
        List<User> userList = Collections.emptyList();
        if (userListOpt.isPresent()){
            userList = userListOpt.get();
        }
        return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
    }
}
