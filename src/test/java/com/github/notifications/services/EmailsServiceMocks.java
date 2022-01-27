package com.github.notifications.services;

import com.github.notifications.payloads.EmailRequest;
import com.google.api.services.gmail.model.Message;
import freemarker.template.Template;

import java.util.Map;
import java.util.UUID;

public class EmailsServiceMocks {

    public static final String emailTemplate = "registration-email.ftl";

    public static EmailRequest validPayload() {
        return new EmailRequest(
                emailTemplate,
                "to@gmail.com",
                "from@gmail.com",
                "Supper subject",
                Map.of()
        );
    }

    public static String template() {
        return "<div>Test email message</div>";
    }

    public static Message message() {
        return new Message().setId(UUID.randomUUID().toString());
    }

}
