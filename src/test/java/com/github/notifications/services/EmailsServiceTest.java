package com.github.notifications.services;

import com.github.gmail.exceptions.ExecuteSendEmailException;
import com.github.gmail.services.EmailSenderService;
import com.github.notifications.configs.EmailServiceApplicationContextConfig;
import com.github.notifications.payloads.EmailRequest;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import reactor.test.StepVerifier;

import java.io.IOException;

import static com.github.notifications.services.EmailsServiceMocks.*;

@SpringBootTest(classes = EmailServiceApplicationContextConfig.class)
@TestPropertySource(properties =
        "spring.autoconfigure.exclude=com.github.gmail.config.EmailConfig"
)
class EmailsServiceTest {

    private final EmailSenderService emailSenderServiceForTestCases;

    private final Configuration templateEngineForTestCases;

    private final EmailsService emailsServiceForTestCases;

    private MockedStatic<FreeMarkerTemplateUtils> freeMarketUtils;

    @Autowired
    public EmailsServiceTest(EmailSenderService emailSenderServiceForTestCases,
                             Configuration templateEngineForTestCases,
                             EmailsService emailsServiceForTestCases) {
        this.emailSenderServiceForTestCases = emailSenderServiceForTestCases;
        this.templateEngineForTestCases = templateEngineForTestCases;
        this.emailsServiceForTestCases = emailsServiceForTestCases;
    }

    @BeforeEach
    void setUp() {
        this.freeMarketUtils = Mockito.mockStatic(FreeMarkerTemplateUtils.class);
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(this.emailSenderServiceForTestCases, this.templateEngineForTestCases);
        this.freeMarketUtils.close();
    }

    @Test
    void givenEmailRequest_whenSendEmail_thenReturnVoid() throws IOException {
        EmailRequest payload = validPayload();
        Template templateMock = Mockito.mock(Template.class);
        BDDMockito.given(this.templateEngineForTestCases.getTemplate(emailTemplate))
                .willReturn(templateMock);
        this.freeMarketUtils.when(() -> FreeMarkerTemplateUtils.processTemplateIntoString(templateMock, payload.getConfigurations()))
                .thenReturn(template());
        Mockito.when(this.emailSenderServiceForTestCases.send(ArgumentMatchers.any()))
                .thenReturn(message());
        StepVerifier.create(this.emailsServiceForTestCases.sendEmail(payload))
                .verifyComplete();
    }

    @Test
    void givenEmailRequest_whenSendEmail_thenThrowExecuteSendEmailException() throws IOException {
        EmailRequest payload = validPayload();
        Template templateMock = Mockito.mock(Template.class);
        BDDMockito.given(this.templateEngineForTestCases.getTemplate(emailTemplate))
                .willReturn(templateMock);
        this.freeMarketUtils.when(() -> FreeMarkerTemplateUtils.processTemplateIntoString(templateMock, payload.getConfigurations()))
                .thenReturn(template());
        Mockito.when(this.emailSenderServiceForTestCases.send(ArgumentMatchers.any()))
                .thenThrow(new ExecuteSendEmailException());
        Assertions.assertThrows(
                ExecuteSendEmailException.class,
                () -> this.emailsServiceForTestCases.sendEmail(payload)
        );
    }

}