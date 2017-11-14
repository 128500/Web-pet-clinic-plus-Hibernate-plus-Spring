package com.llisovichok.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by KUDIN ALEKSANDR on 12.11.2017.
 */

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String  getLoginPage(){
        return "/login";
    }

    @RequestMapping(value = "/redirecting", method = RequestMethod.GET)
    public String redirectToRespectivePage(){
        String page = "wrong_authority_page";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().iterator().next().getAuthority();
        if(role.equals("admin")) page = "/admin/admin_homepage";
        if(role.equals("manager")) page = "/manager/manager_homepage";
        if(role.equals("user")) page = "/user/user_homepage";
        return page;
    }

}
