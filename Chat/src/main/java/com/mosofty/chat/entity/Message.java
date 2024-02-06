package com.mosofty.chat.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Document(collection="Messages")

@Data
public class Message {
    private String source;
    private String destination;
    private String message;
    private LocalDateTime date;
}
