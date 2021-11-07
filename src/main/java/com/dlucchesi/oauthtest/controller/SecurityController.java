package com.dlucchesi.oauthtest.controller;

import com.dlucchesi.oauthtest.model.Const;
import com.dlucchesi.oauthtest.model.imp.UserImp;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecurityController {

    @RequestMapping(value = "/user-auth", method = RequestMethod.GET)
    @ResponseBody
    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN})
    public UserImp user() {
        return (UserImp) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
