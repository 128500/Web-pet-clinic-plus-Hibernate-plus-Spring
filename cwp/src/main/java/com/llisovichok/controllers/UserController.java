package com.llisovichok.controllers;

import com.llisovichok.models.Message;
import com.llisovichok.models.User;
import com.llisovichok.storages.Storages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

/**
 * Created by KUDIN ALEKSANDR on 23.10.2017.
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private Storages storages;

    @RequestMapping(value = "/add_photo/{id}", method = RequestMethod.GET )
    public String addInfo(@PathVariable("id") Integer id, ModelMap model){
        model.addAttribute("user", storages.shHiberStorage.getUser(id));
        return "/user/add_photo";
    }

    @RequestMapping(value = "/save_photo", method = RequestMethod.POST)
    public String addPhoto(@RequestParam("pet_id") Integer petId,
                           @RequestParam("photo") MultipartFile file,
                           ModelMap model){
        byte [] photoBytes = getPhotoBytes(file); // see below for method definition
        boolean result = storages.shHiberStorage.addPhotoWithHibernate(petId, photoBytes);
        if(result) model.addAttribute("message", "THE PHOTO WAS SUCCESSFULLY ADDED");
        else model.addAttribute("message", "COULDN'T ADD THE PHOTO");
        return "/user/show_message";
    }


    /**
     * Retrieves an array of bytes from the given MultipartFile
     * @param file - MultipartFile
     * @return array of retrieved bytes of photo
     */
    private byte [] getPhotoBytes(final MultipartFile file){
        try{
            byte[] data = new byte[1024];
            int nRead;

            try(InputStream fileContent = file.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

                while ((nRead = fileContent.read(data, 0, data.length)) != -1) {
                    baos.write(data, 0, nRead);
                }
                baos.flush();

                return baos.toByteArray();
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        throw new IllegalStateException("Couldn't image bytes");
    }

    /**
     * This method is used as a subsidiary method to retrieve the photo
     * of the pet from database and show it on the web page
     * @param petId - id of the pet
     * @return the retrieved photo as an array of bytes
     */
    @RequestMapping(value = "/load_photo/{petId}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> loadPhoto(@PathVariable("petId") Integer petId) {
        HttpHeaders headers = new HttpHeaders();
        byte[] photo = storages.shHiberStorage.getPetById(petId).getPhoto().getImage();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        return new ResponseEntity<>(photo, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/view_messages/{id}", method = RequestMethod.GET)
    public String getUserMessages(@PathVariable("id") Integer id, ModelMap model){
        Set<Message> messages =  storages.shHiberStorage.getUser(id).getMessages();
        model.addAttribute("messages", messages);
        return "/user/view_messages";
    }

}
