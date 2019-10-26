package com.poly.ps08445.controller.api;

import com.poly.ps08445.dto.UserDTO;
import com.poly.ps08445.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/user")
public class UserAPI {

    @Autowired
    UserService userService;

    @PostMapping(value = "/changePassword", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Boolean changePassword(HttpSession session, @SessionAttribute("USER") UserDTO userSession, @RequestBody UserDTO userDTO){
        userSession.setPassword(userDTO.getNewPassword());
        if (userService.updateUser(userSession)){
            session.setAttribute("USER", userSession);
            return true;
        }
        return false;
    }

}
