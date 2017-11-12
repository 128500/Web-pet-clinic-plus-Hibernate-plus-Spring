package com.llisovichok.controllers;

import com.llisovichok.models.Message;
import com.llisovichok.models.User;
import com.llisovichok.storages.Storages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by KUDIN ALEKSANDR on 11.11.2017.
 */

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private Storages storages;

    @ModelAttribute("user")
    public User getUserModel(){
        return new User();
    }

    @RequestMapping(value="/create_account", method = RequestMethod.GET)
    public ModelAndView createAccount(){
        return new ModelAndView("manager/create_account");
    }


    @RequestMapping(value = "/add_user", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("user") User user){
        storages.shHiberStorage.addUser(user);
        return new ModelAndView("redirect:/admin/view_users");
    }


    @RequestMapping(value = "/edit_profile/{id}", method = RequestMethod.GET)
    public String editProfile(@PathVariable("id") Integer id, ModelMap model){
        model.addAttribute("user", storages.shHiberStorage.getUser(id));
        return "/manager/edit_profile";
    }

    @RequestMapping(value = "/edit_profile", method = RequestMethod.POST)
    public String saveProfileChanges(@ModelAttribute("user") User user, ModelMap model){
        boolean result = storages.shHiberStorage.editUser(user.getId(), user);
        if(result) model.addAttribute("message", "PROFILE WAS SUCCESSFULLY EDITED");
        else model.addAttribute("message", "SORRY, BUT WE COULDN'T ADD CHANGES TO YOUR PROFILE");
        return "/user/show_message";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") Integer id){
        storages.shHiberStorage.removeUser(id);
        return "redirect:/admin/view_users";
    }

    @RequestMapping(value = "/send_message/{id}", method = RequestMethod.GET)
    public String writeMessage(@PathVariable("id") Integer id, ModelMap model){
        model.addAttribute("user", storages.shHiberStorage.getUser(id));
        return "manager/send_message";
    }

    @RequestMapping(value = "/save_message/{id}", method = RequestMethod.POST)
    public String saveMessage(@PathVariable("id")Integer id,
                              @RequestParam("message") String text,
                              ModelMap model){
        User user = storages.shHiberStorage.getUser(id);
        Message message = new Message(text);
        message.setUser(user);
        user.getMessages().add(message);
        boolean result  = storages.shHiberStorage.editUser(user.getId(), user);
        if(result) model.addAttribute("message", "THE MESSAGE WAS SENT SUCCESSFULLY");
        else model.addAttribute("message", "SORRY, BUT WE COULDN'T SEND THE MESSAGE");
        return "/user/show_message";
    }
}
