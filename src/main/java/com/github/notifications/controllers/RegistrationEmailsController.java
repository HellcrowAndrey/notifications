package com.github.notifications.controllers;

import com.github.notifications.payloads.EmailRequest;
import com.github.notifications.services.RegistrationEmailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class RegistrationEmailsController {

    private final RegistrationEmailsService registrationEmailsService;

    @MessageMapping(value = "registration-email")
    public void registrationEmail(EmailRequest payload) {
        this.registrationEmailsService.send();
    }

}
