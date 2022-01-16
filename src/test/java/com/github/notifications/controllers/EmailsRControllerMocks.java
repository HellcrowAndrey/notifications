package com.github.notifications.controllers;

import com.github.notifications.payloads.EmailRequest;

import java.util.Map;

public class EmailsRControllerMocks {

    public static EmailRequest payload() {
        return new EmailRequest(
                "registration-tmp",
                "to@gmail.com",
                "from@gmail.com",
                "Supper subject",
                Map.of()
        );
    }

}
