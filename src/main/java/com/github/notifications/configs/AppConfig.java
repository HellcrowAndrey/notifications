package com.github.notifications.configs;

import com.github.gmail.decorators.AuthLinkBrowser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class AppConfig {

    @Bean
    public AuthLinkBrowser authLinkBrowser() {
        return new AuthLinkBrowser(linkToAuth -> log.info("Enter: {}", linkToAuth));
    }

}
