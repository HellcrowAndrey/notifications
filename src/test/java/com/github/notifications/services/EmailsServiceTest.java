package com.github.notifications.services;

import com.github.gmail.exceptions.ExecuteSendEmailException;
import com.github.gmail.services.EmailSenderService;
import com.github.notifications.configs.EmailServiceApplicationContextConfig;
import com.github.notifications.payloads.EmailRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.thymeleaf.TemplateEngine;
import reactor.test.StepVerifier;

import static com.github.notifications.services.EmailsServiceMocks.*;

@SpringBootTest(classes = EmailServiceApplicationContextConfig.class)
@TestPropertySource(properties =
        "spring.autoconfigure.exclude=com.github.gmail.config.EmailConfig")
class EmailsServiceTest {

    private final EmailSenderService emailSenderServiceForTestCases;

    private final TemplateEngine templateEngineForTestCases;

    private final EmailsService emailsServiceForTestCases;

    @Autowired
    public EmailsServiceTest(EmailSenderService emailSenderServiceForTestCases,
                             TemplateEngine templateEngineForTestCases,
                             EmailsService emailsServiceForTestCases) {
        this.emailSenderServiceForTestCases = emailSenderServiceForTestCases;
        this.templateEngineForTestCases = templateEngineForTestCases;
        this.emailsServiceForTestCases = emailsServiceForTestCases;
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void givenEmailRequest_whenSendEmail_thenReturnVoid() {
        EmailRequest payload = validPayload();
        BDDMockito.given(this.templateEngineForTestCases.process(emailTemplate, contextForLoadTemplate()))
                .willReturn(template());
        Mockito.when(this.emailSenderServiceForTestCases.send(ArgumentMatchers.any()))
                .thenReturn(message());
        StepVerifier.create(this.emailsServiceForTestCases.sendEmail(payload))
                .verifyComplete();
    }

    @Test
    void givenEmailRequest_whenSendEmail_thenThrowExecuteSendEmailException() {
        EmailRequest payload = validPayload();
        BDDMockito.given(this.templateEngineForTestCases.process(emailTemplate, contextForLoadTemplate()))
                .willReturn(template());
        Mockito.when(this.emailSenderServiceForTestCases.send(ArgumentMatchers.any()))
                .thenThrow(new ExecuteSendEmailException());
        Assertions.assertThrows(ExecuteSendEmailException.class, () -> this.emailsServiceForTestCases.sendEmail(payload));
    }

}