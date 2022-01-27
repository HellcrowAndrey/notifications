package com.github.notifications.services;

import com.github.gmail.exceptions.ExecuteSendEmailException;
import com.github.gmail.services.EmailSenderService;
import com.github.notifications.payloads.EmailRequest;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import reactor.core.publisher.Mono;

import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static com.github.gmail.utils.EmailMessageUtils.SUBTYPE_HTML;
import static com.github.gmail.utils.EmailMessageUtils.ofEmailWithText;

@Service
@RequiredArgsConstructor
public class EmailsService {

    private final EmailSenderService emailSender;

    private final Configuration templateEngine;

    public Mono<Void> sendEmail(EmailRequest request) {
        MimeMessage payload = ofEmailWithText(
                request.getTo(), request.getFrom(),
                request.getSubject(), loadTemplate(request.getTemplate(), request.getConfigurations()),
                StandardCharsets.UTF_8.name(), SUBTYPE_HTML
        );
        return Mono.just(this.emailSender.send(payload)).then();
    }

    private String loadTemplate(String templateName, Map<String, Object> configurations) {
        try {
            Template template = this.templateEngine.getTemplate(templateName + ".ftl");
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, configurations);
        } catch (IOException | TemplateException e) {
            throw new ExecuteSendEmailException(e.getMessage());
        }
    }

}
