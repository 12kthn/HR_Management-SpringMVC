package com.poly.ps08445.controller.api;

import com.poly.ps08445.dto.EmailDTO;
import com.poly.ps08445.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mailer")
public class MailerAPI {

    @Autowired
    MailService mailService;

    @PostMapping(value = "/send", produces = "application/json; charset=UTF-8")
    public boolean send(@RequestBody EmailDTO emailModel){
        return mailService.sendEmail(emailModel);
    }

}
