package com.github.notifications.configs;

import com.github.gmail.services.EmailSenderService;
import com.github.notifications.services.EmailsService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.TemplateEngine;

@TestConfiguration
public class EmailServiceApplicationContextConfig {

    @Bean
    public EmailSenderService emailSenderServiceForTestCases() {
        return Mockito.mock(EmailSenderService.class);
    }

    @Bean
    public TemplateEngine templateEngineForTestCases() {
        return Mockito.mock(TemplateEngine.class);
    }

    @Bean
    public EmailsService emailsServiceForTestCases(EmailSenderService emailSenderServiceForTestCases,
                                                   TemplateEngine templateEngineForTestCases) {
        return new EmailsService(
                emailSenderServiceForTestCases,
                templateEngineForTestCases
        );
    }

}
