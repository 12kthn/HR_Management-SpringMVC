package com.poly.ps08445.services.impl;

import com.poly.ps08445.dto.EmailDTO;
import com.poly.ps08445.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailServicesImpl implements MailService {

    @Autowired
    JavaMailSender mailer;

    @Override
    public boolean sendEmail(EmailDTO emailmodel) {
        try {
            MimeMessage mail = mailer.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true, "utf-8");

            helper.setTo(emailmodel.getTo());
            helper.setSubject(emailmodel.getSubject());
            helper.setText(emailmodel.getBody(), true);
            helper.addCc(emailmodel.getCc());
            helper.addBcc(emailmodel.getBcc());
            // Gửi mail
            mailer.send(mail);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
