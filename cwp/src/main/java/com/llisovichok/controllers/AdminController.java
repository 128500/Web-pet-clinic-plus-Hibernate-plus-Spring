package com.llisovichok.controllers;

import com.llisovichok.models.User;
import com.llisovichok.storages.Storages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KUDIN ALEKSANDR on 23.10.2017.
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private Storages storages;

    @RequestMapping(value = "/view_users", method = RequestMethod.GET)
    public String showUsers(ModelMap model){
        model.addAttribute("users", storages.shHiberStorage.values());
        return "admin/view_users";
    }

    @RequestMapping(value = "/find_users", method = RequestMethod.POST)
    public String findUsers(@RequestParam(value = "input", defaultValue = "") String input,
                            @RequestParam(value = "first_name", defaultValue = "false") boolean fn,
                            @RequestParam(value = "last_name", defaultValue = "false") boolean ln,
                            @RequestParam(value = "pet_name", defaultValue = "false") boolean pn,
                            @RequestParam(value = "address", defaultValue = "false") boolean ad,
                            ModelMap model){
        List<User> foundUsers = new ArrayList<>( storages.shHiberStorage.findUsers(input, fn, ln, pn));

        if(foundUsers.isEmpty()) return "/admin/failed_result_of_searching";
        else{
            model.addAttribute("foundUsers", foundUsers);
            return "/admin/result_of_searching";
        }
    }
}
