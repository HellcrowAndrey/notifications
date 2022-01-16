package com.github.notifications;

import com.github.notifications.payloads.EmailRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Map;

@SpringBootTest()
//@ActiveProfiles(value = "test")
public class RSocketClientServiceIT {

    @Autowired
   private RSocketRequester.Builder builder;

    @Test
    public void t() {
        Mono<Void> r = builder.tcp("localhost", 7000)
                .route("emails")
                .data(new EmailRequest(
                        "registration-email",
                        "todo.list.email.api@gmail.com",
                        "bakamakafoqq@gmail.com",
                        "This is totorutoo",
                        Map.of()
                )).send();
        StepVerifier.create(r)
                .verifyComplete();
    }

}
