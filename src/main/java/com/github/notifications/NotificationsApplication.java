package com.github.notifications;

import com.github.notifications.services.RegistrationEmailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class NotificationsApplication implements ApplicationRunner {

    private final RegistrationEmailsService registrationEmailsService;

    public static void main(String[] args) {
        SpringApplication.run(NotificationsApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.registrationEmailsService.sendRegistrationEmail(null);
    }
}
