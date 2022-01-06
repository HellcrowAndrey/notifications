package com.github.notifications.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequest {

    private String to;

    private String from;

    private Map<Object, Object> configurations;

}
