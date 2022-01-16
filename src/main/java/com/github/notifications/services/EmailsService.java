package com.github.notifications.services;

import com.github.gmail.services.EmailSenderService;
import com.github.notifications.payloads.EmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import reactor.core.publisher.Mono;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Map;

import static com.github.gmail.utils.EmailMessageUtils.SUBTYPE_HTML;
import static com.github.gmail.utils.EmailMessageUtils.ofEmailWithText;

@Service
@RequiredArgsConstructor
public class EmailsService {

    private final EmailSenderService emailSender;

    private final TemplateEngine templateEngine;

    public Mono<Void> sendEmail(EmailRequest request) {
        MimeMessage payload = ofEmailWithText(
                request.getTo(), request.getFrom(),
                request.getSubject(), loadTemplate(request.getTemplate(), request.getConfigurations()),
                StandardCharsets.UTF_8.name(), SUBTYPE_HTML
        );
        return Mono.just(this.emailSender.send(payload)).then();
    }

    private String loadTemplate(String template, Map<String, Object> configurations) {
        return this.templateEngine.process(
                template,
                new Context(Locale.getDefault(), configurations)
        );
    }

}
