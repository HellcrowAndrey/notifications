package com.github.notifications.controllers;

import com.github.notifications.payloads.EmailRequest;
import com.github.notifications.services.EmailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class EmailsRController {

    private final EmailsService emailsService;

    @MessageMapping(value = "emails")
    public Mono<Void> sendEmail(EmailRequest payload) {
        return this.emailsService.sendEmail(payload);
    }

}
