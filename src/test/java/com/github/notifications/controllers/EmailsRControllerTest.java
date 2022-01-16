package com.github.notifications.controllers;

import com.github.notifications.configs.EmailsRControllerApplicationConfig;
import com.github.notifications.payloads.EmailRequest;
import com.github.notifications.services.EmailsService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.github.notifications.controllers.EmailsRControllerMocks.payload;

@SpringBootTest(classes = EmailsRControllerApplicationConfig.class)
@TestPropertySource(properties =
        "spring.autoconfigure.exclude=com.github.gmail.config.EmailConfig"
)
class EmailsRControllerTest {

    private final EmailsService emailsServiceTestCases;

    private final EmailsRController emailsRControllerTestCases;

    @Autowired
    public EmailsRControllerTest(EmailsService emailsServiceTestCases, EmailsRController emailsRControllerTestCases) {
        this.emailsServiceTestCases = emailsServiceTestCases;
        this.emailsRControllerTestCases = emailsRControllerTestCases;
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void givenEmailRequest_whenSendEmail_thenReturnVoid() {
        EmailRequest payload = payload();
        Mockito.when(this.emailsServiceTestCases.sendEmail(payload))
                .thenReturn(Mono.empty());
        StepVerifier.create(this.emailsRControllerTestCases.sendEmail(payload))
                .verifyComplete();
    }

}