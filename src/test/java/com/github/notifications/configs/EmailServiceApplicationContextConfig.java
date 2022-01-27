package com.github.notifications.configs;

import com.github.gmail.services.EmailSenderService;
import com.github.notifications.services.EmailsService;
import freemarker.template.Configuration;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class EmailServiceApplicationContextConfig {

    @Bean
    public EmailSenderService emailSenderServiceForTestCases() {
        return Mockito.mock(EmailSenderService.class);
    }

    @Bean
    public Configuration templateEngineForTestCases() {
        return Mockito.mock(Configuration.class);
    }

    @Bean
    public EmailsService emailsServiceForTestCases(EmailSenderService emailSenderServiceForTestCases,
                                                   Configuration templateEngineForTestCases) {
        return new EmailsService(
                emailSenderServiceForTestCases,
                templateEngineForTestCases
        );
    }

}
