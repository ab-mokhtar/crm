package com.mosofty.chat.entity;

import lombok.Data;

@Data
public class MessageRequest {
    private String message;
    private String listenerId;
}
