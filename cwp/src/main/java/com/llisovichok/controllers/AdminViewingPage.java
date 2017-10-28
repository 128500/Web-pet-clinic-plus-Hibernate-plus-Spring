package com.llisovichok.controllers;

import com.llisovichok.storages.Storages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by KUDIN ALEKSANDR on 23.10.2017.
 */

@Controller
@RequestMapping("/admin")
public class AdminViewingPage {

    @Autowired
    private Storages storages;

    @RequestMapping(value = "/view_users", method = RequestMethod.GET)
    public String showUsers(ModelMap model){
        model.addAttribute("users", storages.shHiberStorage.values());
        return "admin/view_users";
    }
}
