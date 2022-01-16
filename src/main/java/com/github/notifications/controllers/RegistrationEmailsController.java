package com.github.notifications.controllers;

import com.github.notifications.payloads.EmailRequest;
import com.github.notifications.services.EmailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class RegistrationEmailsController {

    private final EmailsService registrationEmailsService;

    @MessageMapping(value = "emails")
    public Mono<Void> registrationEmail(EmailRequest payload) {
        return this.registrationEmailsService.sendEmail(payload);
    }

}
