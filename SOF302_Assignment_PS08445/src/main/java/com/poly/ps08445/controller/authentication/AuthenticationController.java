package com.poly.ps08445.controller.authentication;

import com.poly.ps08445.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller(value = "authentication")
@RequestMapping(value = "/authentication")
public class AuthenticationController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String displayLoginForm(){
        return "authentication/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("USER");
        return "redirect: " + request.getContextPath() + "/authentication/login";
    }

}
