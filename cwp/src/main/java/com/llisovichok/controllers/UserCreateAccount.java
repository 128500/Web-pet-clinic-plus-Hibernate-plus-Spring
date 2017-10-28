package com.llisovichok.controllers;

import com.llisovichok.lessons.clinic.Pet;
import com.llisovichok.models.User;
import com.llisovichok.storages.Storages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by KUDIN ALEKSANDR on 23.10.2017.
 */

@Controller
@RequestMapping("/user")
public class UserCreateAccount {

    @Autowired
    private Storages storages;

    @ModelAttribute("user")
    public User getUserModel(){
        return new User();
    }

    @RequestMapping(value="/create_account", method = RequestMethod.GET)
    public ModelAndView createAccount(){
         return new ModelAndView("user/create_account");
    }

    @RequestMapping(value = "/add_user", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("user") User user, BindingResult bindingResult){
        storages.shHiberStorage.addUser(user);
        return new ModelAndView("redirect:/admin/view_users");
    }
}
