package com.github.notifications.configs;

import com.github.notifications.controllers.EmailsRController;
import com.github.notifications.services.EmailsService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class EmailsRControllerApplicationConfig {

    @Bean
    public EmailsService emailsServiceTestCases() {
        return Mockito.mock(EmailsService.class);
    }

    @Bean
    public EmailsRController emailsRControllerTestCases(EmailsService emailsServiceTestCases) {
        return new EmailsRController(emailsServiceTestCases);
    }

}
