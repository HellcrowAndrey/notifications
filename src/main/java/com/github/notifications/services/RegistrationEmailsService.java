package com.github.notifications.services;

import com.github.gmail.services.EmailSenderService;
import com.github.notifications.payloads.EmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Map;

import static com.github.gmail.utils.EmailMessageUtils.SUBTYPE_HTML;
import static com.github.gmail.utils.EmailMessageUtils.ofEmailWithText;

@Service
@RequiredArgsConstructor
public class RegistrationEmailsService {

    private static final String REGISTRATION_EMAIL_TEMPLATE = "registration-email";

    private final EmailSenderService emailSender;

    private final TemplateEngine templateEngine;

    public void sendRegistrationEmail(EmailRequest request) {
        var template = loadTemplate(request.getConfigurations());
        MimeMessage payload = ofEmailWithText(
                request.getTo(), request.getFrom(),
                request.getSubject(), template,
                StandardCharsets.UTF_8.name(), SUBTYPE_HTML
        );
        this.emailSender.send(payload);
    }

    private String loadTemplate(Map<String, Object> configurations) {
        return this.templateEngine.process(
                REGISTRATION_EMAIL_TEMPLATE,
                new Context(Locale.getDefault(), configurations)
        );
    }

}
