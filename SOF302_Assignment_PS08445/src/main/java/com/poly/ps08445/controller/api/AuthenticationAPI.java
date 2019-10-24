package com.poly.ps08445.controller.api;

import com.poly.ps08445.dto.UserDTO;
import com.poly.ps08445.entities.User;
import com.poly.ps08445.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationAPI {

    @Autowired
    UserService userService;

    @Autowired
    ServletContext context;

    @PostMapping(value = "/login", produces = "application/json; charset=UTF-8")
    public boolean login(@RequestBody UserDTO userDTO, HttpServletResponse response, HttpSession session){
        User user = userService.findByUserNamePassword(userDTO);
        if (user != null){
            userDTO.setId(user.getId());
            session.setAttribute("USER", userDTO);

            Cookie ckiUserName = new Cookie("ckiUserName", userDTO.getUserName());
            Cookie ckiPassword = new Cookie("ckiPassword", userDTO.getPassword());

            ckiUserName.setMaxAge(userDTO.getSaveAccount() ? 60*60*24*30 : 0);
            ckiUserName.setPath(context.getContextPath() + "/authentication/login");

            ckiPassword.setMaxAge(userDTO.getSaveAccount() ? 60*60*24*30 : 0);
            ckiPassword.setPath(context.getContextPath() + "/authentication/login");

            response.addCookie(ckiUserName);
            response.addCookie(ckiPassword);

            return true;
        } else {
            return false;
        }
    }


}
