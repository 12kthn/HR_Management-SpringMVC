package com.poly.ps08445.services;

import com.poly.ps08445.dto.EmailDTO;

public interface MailService {

    boolean sendEmail(EmailDTO emailmodel);

}
